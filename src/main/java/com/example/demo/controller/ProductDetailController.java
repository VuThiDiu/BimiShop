package com.example.demo.controller;


import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class ProductDetailController {
    @Autowired
    ProductService productService;

    @GetMapping("/detail/{id}")
    public String getProductDetail(Model model, @PathVariable("id") String id){
        Product product = productService.getProduct(id);
        ProductDTO productDTO = ProductDTO.from(product);
        model.addAttribute("product", productDTO);
        return "productDetail";
    }

    @PutMapping("/view")
    public void updateView(@RequestParam String id){
        productService.updateViewProductById(id);
    }
}
