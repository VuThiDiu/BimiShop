package com.example.demo.controller;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomePage {

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
