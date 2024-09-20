package com.example.ecommerce.Backend.Controller.UserController;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.Backend.Dtos.userDTO.UserDTO;
import com.example.ecommerce.Backend.IService.iUserService.IUserService;
import com.example.ecommerce.Backend.Modals.User;
import com.example.ecommerce.Backend.Responses.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO,BindingResult result){
        try {
            if(result.hasErrors()){
                List<String> errors = result.getFieldErrors().stream()
                                            .map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errors);
            }
            userService.createUser(userDTO);
            return ResponseEntity.ok("Register successfull"+userDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/updatePass/{id}")
    public ResponseEntity<ApiResponse> rePassword(@PathVariable Long id, @RequestBody UserDTO userDTO){
        try {
            userService.updatePassword(id, userDTO);
            ApiResponse response = ApiResponse.builder()
                    .message("Password updated successfully")
                    .status(200)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = ApiResponse.builder()
                    .message(e.getMessage())
                    .status(400)
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        try {
            User loggedInuser = userService.login(userDTO);
            return ResponseEntity.ok("Login success, welcome "+loggedInuser.getName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
        
}
