package com.example.demo.controller;
import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.service.impl.ProductService;
import com.example.demo.service.impl.UserService;
import com.example.demo.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class HomeController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/home")
    public String getHome(@RequestParam(defaultValue = "0" )int pageNumber,
                          @RequestParam(defaultValue = "10") int pageSize, Model model,
                          HttpServletRequest httpServletRequest){
        Page<Product> pagedResult = productService.listProduct(pageNumber, pageSize, "numberOfViews");
        List<Product> products = new ArrayList<Product>();
        if(pagedResult.hasContent()){
            products =  pagedResult.getContent();
        }
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product :products){
            productDTOS.add(ProductDTO.from(product));
        }
        model.addAttribute("products", productDTOS);
        model.addAttribute("totalPage", pagedResult.getTotalPages());
        return "home";
    }
}
