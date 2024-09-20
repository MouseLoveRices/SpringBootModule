package com.example.springSecurity.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("api/category")
public class CategoryController {
    @GetMapping("/")
    public String index() {
        return "this is category";
    }
    @GetMapping("/login")
    public String login() {
        return "this is login category";
    }
    
}
