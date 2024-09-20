package com.example.springSecurity.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springSecurity.models.User;
import com.example.springSecurity.response.ApiResponse;
import com.example.springSecurity.services.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RestController
@RequestMapping("api/user")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> index1(){
        ApiResponse apiResponse = ApiResponse.builder() 
                                .data(userService.getAllUser())
                                .message("Get all successfull")
                                .status(HttpStatus.OK.value())
                                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Validated @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                                    .map(FieldError::getDefaultMessage).toList();
            ApiResponse apiResponse = ApiResponse.builder()
                                .data(errors)
                                .message("Validation failed")
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }
        User user1 = userService.createUser(user);
        ApiResponse apiResponse = ApiResponse.builder()
                                .data(user1)
                                .message("Insert successfully")
                                .status(HttpStatus.OK.value())
                                .build();
        return ResponseEntity.ok().body(apiResponse);
    
    }







    @GetMapping("/")
    public String index() {
        return "this is list user";
    }

    @GetMapping("/login")
    public String login() {
        return "login user";
    }
    
    
}
