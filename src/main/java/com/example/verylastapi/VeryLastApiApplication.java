package com.example.verylastapi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class VeryLastApiApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(VeryLastApiApplication.class, args);
    }

}
