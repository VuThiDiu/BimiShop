package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")

public class NewestPageController {
    @GetMapping("/newest")
    public String getNewestPage(){
        return "newest";
    }
}
