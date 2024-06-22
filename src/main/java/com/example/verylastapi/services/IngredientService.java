package com.example.verylastapi.services;

import com.example.verylastapi.classes.models.Cocktail;
import com.example.verylastapi.classes.models.Ingredient;
import com.example.verylastapi.classes.requests.IngredientAdditionRequest;
import com.example.verylastapi.respositories.CocktailRespository;
import com.example.verylastapi.respositories.IngredientRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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


    public void addNewIngredient(IngredientAdditionRequest ingredient, Long id) {
        Cocktail cocktail = respository1.findById(id).get();
        if(cocktail == null) throw new NoSuchElementException();
        Ingredient ingredientToDB= Ingredient.builder()
                .unit(ingredient.getUnit())
                .name(ingredient.getName())
                .quantity(ingredient.getQuantity())
                .cocktail(cocktail)
                .build();
        respository.save(ingredientToDB);
    }
}
