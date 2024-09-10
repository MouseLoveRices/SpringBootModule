package com.example.buoi5.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/layout")
public class LayoutController {
    @GetMapping("/layout1")
    public String layout1(){
        return "this is layout1";
    }

    @GetMapping("/layout2")
    public String layout2(){
        return "This is layout 2";
    }
}
