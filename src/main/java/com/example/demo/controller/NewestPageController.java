package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")

public class NewestPageController {
    @Autowired
    ProductService productService;
    @GetMapping("/newest")
    public String getNewestPage(@RequestParam(defaultValue = "0" )int pageNumber,
                                    @RequestParam(defaultValue = "10") int pageSize, Model model){
        Page<Product> pagedResult = productService.listProduct(pageNumber, pageSize, "dateTime");
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
        return "newest";
    }
}
