package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AccountPage {
    @GetMapping("/account/{id}")
    public String getHomePage(@PathVariable("id") String id){
        return "account";
    }
}
