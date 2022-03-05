package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;
    @GetMapping("/register")
    public String getRegisterPage(){
        return "register";
    }
    // oke
    @ResponseBody
    @PostMapping(value = "/register", produces = "application/json")
    public User Register( User user){
        String userName = user.getUsername();
        if(userName!=null && userName.length()>0){
            userService.createNewUser(user);
        }
        System.out.println(user);
        return user;
    }
}
