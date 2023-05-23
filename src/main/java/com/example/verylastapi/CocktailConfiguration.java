package com.example.verylastapi;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.respositories.CocktailRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class CocktailConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(CocktailRespository cocktailRespository)
    {
        return args -> {


            Cocktail p=new Cocktail("y","t",";");

            cocktailRespository.saveAll(List.of(p));
        };
    }
}
