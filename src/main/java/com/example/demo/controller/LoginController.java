package com.example.demo.controller;


import com.example.demo.JwtTokenProvider;
import com.example.demo.LoginResponse;
import com.example.demo.dto.UserLogin;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserService userService;
    @GetMapping(value = {"/login", "/", ""})
    public String getLoginForm( Model model, HttpServletRequest httpServletRequest){
        model.addAttribute("loginForm", new UserLogin());
//        model.addAttribute("url", WebUtils.buildUrlForPaging(httpServletRequest, "/login"));
        return "login";
    }
    // ok ne
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity authentication(@ModelAttribute("loginForm") UserLogin user){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );
            User user1 = (User) userService.getUserByUserName(user.getUsername());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            return  new ResponseEntity(new LoginResponse(jwt,  user1.getId(),user1.getUsername()), HttpStatus.OK);
        }catch (Exception e){
            LOGGER.debug(e.toString());
        }
        return  new ResponseEntity(new LoginResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

