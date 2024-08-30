package com.example.buoi1SpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


@SpringBootApplication
@RestController
public class Buoi1SpringBootApplication {

	public static void main(String[] args) {
        SpringApplication.run(Buoi1SpringBootApplication.class, args);
    }

    @GetMapping("/greeting")
    public Greeting receiveGreeting(@RequestBody Greeting greeting) {
        // Xử lý yêu cầu POST
        System.out.println("Received message: " + greeting.getName());
        return greeting;
    }
	

}
