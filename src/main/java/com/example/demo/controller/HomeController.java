package com.example.demo.controller;
import com.example.demo.dto.SearchForm;
import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.service.impl.ProductService;
import com.example.demo.service.impl.UserService;
import com.example.demo.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class HomeController {

    @Autowired
    ProductService productService;
    @GetMapping(value = "/test")
    public String test(@RequestParam int a){
        return "done";
    }
    @GetMapping(value = "/home")
    public String getHome(Model model,
                          @RequestParam (required = false) Integer costFrom,
                          @RequestParam (required = false) Integer costTo,
                          @RequestParam (required = false) String category,
                          @RequestParam (required = false) String color
                          ){
            if(costFrom == null) costFrom = 0;
            if(costTo == null) costTo = 0;
            if(category == null || category.equals("null")) category = "%";
            if(color == null || color.equals("null")) color = "%";
        SearchForm searchForm = new SearchForm(costFrom, costTo,  category, color);
        List<Product> products = productService.listProduct("numberOfViews", searchForm);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product :products){
            productDTOS.add(ProductDTO.from(product));
        }
        model.addAttribute("products", productDTOS);
        return "home";
    }
}
