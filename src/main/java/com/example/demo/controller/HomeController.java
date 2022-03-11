package com.example.demo.controller;
import com.example.demo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/home")
    public String getHome(){
//        User user = userService.getUserById(id);
//        UserDTO userDTO  =  UserDTO.from(user);
//        model.addAttribute("todolist", userDTO.getTodoDTO());
        return "home";
    }
}
