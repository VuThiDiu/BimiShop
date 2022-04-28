package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class SaleController {

    @Value("${autoTaggingSystem.path}")
    private String autoTaggingSystemPath;
    @GetMapping("/sale")
    public String getSalePage(Model model){
        model.addAttribute("url", autoTaggingSystemPath );
        return "selling";
    }
}
