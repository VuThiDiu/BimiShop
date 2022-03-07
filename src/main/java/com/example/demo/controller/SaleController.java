package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class SaleController {
    @GetMapping("/sale/{id}")
    public String getSalePage(@PathVariable("id") String id){
        return "selling";
    }
}
