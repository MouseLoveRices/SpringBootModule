package com.example.buoi3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class Buoi3Application {

	public static void main(String[] args) {
		SpringApplication.run(Buoi3Application.class, args);
	}

}
