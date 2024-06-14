package com.example.verylastapi.services;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.classes.Ingredient;
import com.example.verylastapi.respositories.CocktailRespository;
import com.example.verylastapi.respositories.IngredientRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class IngredientService {
    private final IngredientRespository respository;
    private final CocktailRespository respository1;

    @Autowired
    public IngredientService(IngredientRespository respository, CocktailRespository respository1) {
        this.respository = respository;
        this.respository1=respository1;
    }
    public List<Ingredient> GetAllIngredients(Long Id) {

        return respository.findByCocktailId(Id);
    }


    public void addNewIngredients(Ingredient ingredients, int id) {
        Cocktail cocktail=respository1//refactor
                .findAll()
                .get(id);
        ingredients.setCocktail(cocktail);
        respository.save(ingredients);
    }
}
