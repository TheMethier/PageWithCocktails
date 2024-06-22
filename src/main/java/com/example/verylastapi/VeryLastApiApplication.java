package com.example.verylastapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
@Profile("!test")

@SpringBootApplication
public class VeryLastApiApplication {

    public static void main(String[] args) throws IOException {


        SpringApplication.run(VeryLastApiApplication.class, args);
    }

}
