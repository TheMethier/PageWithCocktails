package com.example.verylastapi;

import com.example.verylastapi.controllers.ScraperController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class VeryLastApiApplication {

    public static void main(String[] args) throws IOException {
        
        SpringApplication.run(VeryLastApiApplication.class, args);
    }

}
