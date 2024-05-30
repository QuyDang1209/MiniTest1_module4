package com.cg.minitest1module4.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class UserController {
    @GetMapping
    public String index(){
        System.out.println("KKK");
        return "index";
    }
    @GetMapping("/user")
    public String user(Principal principal){
        System.out.println(principal);
        return "user";
    }
    @GetMapping("/admin")
    public String admin(){
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context.getAuthentication().getName());
        return "admin";
    }
}
