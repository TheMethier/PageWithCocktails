package com.example.verylastapi;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.classes.Ingredients;
import com.example.verylastapi.controllers.ScraperController;
import com.example.verylastapi.respositories.CocktailRespository;
import com.example.verylastapi.respositories.IngredientRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.*;

@Configuration
public class CocktailConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(CocktailRespository cocktailRespository, IngredientRespository ingredientRespository)
    {
        return args -> {
            ScraperController scraperController= new ScraperController();
            /*Set <Ingredients> p =new HashSet<>();
            Cocktail w=new Cocktail("y","t",p);
            p.add(new Ingredients(w,"n", 15F,"ml"));
            cocktailRespository.saveAll(List.of(w));
            */
                List<Cocktail> cocktails=scraperController.ScrapMyCocktail();
                List<Ingredients> ingredients=scraperController.ScrapMyI(cocktails);
                cocktailRespository.saveAll(cocktails);
                ingredientRespository.saveAll(ingredients);


        };
    }
}
