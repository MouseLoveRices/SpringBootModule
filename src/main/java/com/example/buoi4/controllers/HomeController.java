package com.example.buoi4.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class HomeController {
    @GetMapping("/")
    public String homePage() {
        return "This is home page";
    }
    
    @GetMapping("/product")
    public String productPage() {
        return "This is product page";
    }

}
