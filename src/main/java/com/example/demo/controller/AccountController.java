package com.example.demo.controller;


import com.example.demo.dto.SearchForm;
import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class AccountController {

    @Autowired
    ProductService productService;

    @GetMapping("/account/{id}")
    public String getHomePage(Model model, @PathVariable("id") String id){
        model.addAttribute("userId", id);
        return "account";
    }

    @GetMapping("/myProduct/{id}")
    public String getMyProduct(Model model, @PathVariable("id") String userID){
        // get list Product with userID
        List<Product> products = productService.getProductsByUserID(userID);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product :products){
            productDTOS.add(ProductDTO.from(product));
        }
        model.addAttribute("products", productDTOS);
        return "myProduct";
    }

}
