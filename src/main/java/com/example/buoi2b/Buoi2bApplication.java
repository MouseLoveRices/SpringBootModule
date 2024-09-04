package com.example.buoi2b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class Buoi2bApplication {

	public static void main(String[] args) {
		SpringApplication.run(Buoi2bApplication.class, args);
	}
  
}
