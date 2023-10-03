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

       /* String key="BCEA51CBDA7EDDA227165F7BE2A58";
        String link="https://acte.ltd/utils/randomkeygen";
        try {
            Document doc = Jsoup.connect(link).get();
            Element keys= doc.select("tr").first();
            System.out.println(keys);
        } catch (IOException e) {

        }
        System.out.println(key);
*/

        SpringApplication.run(VeryLastApiApplication.class, args);
    }

}
