package com.example.buoi2b;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository homeRepo;


    @GetMapping("/")
    public ResponseEntity<List<User>> getAll(@RequestParam(required = false) String title){
        try {
            List<User> homes = new ArrayList<User>();


            homeRepo.findAll().forEach(homes::add);


            // if (title == null)
            //     homeRepo.findAll().forEach(homes::add);
            // else
            //     homeRepo.findByPublished(true);
            if (homes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(homes, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

