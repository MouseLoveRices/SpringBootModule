package com.example.buoi5.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    @GetMapping("/admin1")
    public String index1(){
        return "Admin page1";
    }

    @GetMapping("/admin2")
    public String index2(){
        return "Admin page2";
    }
}
