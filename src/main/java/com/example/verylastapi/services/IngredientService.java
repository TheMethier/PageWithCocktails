package com.example.verylastapi.services;

import com.example.verylastapi.classes.Ingredients;
import com.example.verylastapi.respositories.IngredientRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service

public class IngredientService {
    private final IngredientRespository respository;

    @Autowired
    public IngredientService(IngredientRespository respository) {
        this.respository = respository;
    }
    public List<Ingredients> GetAllIngredients(Long Id) {
        return respository.findByCocktailId(Id);
    }
}
