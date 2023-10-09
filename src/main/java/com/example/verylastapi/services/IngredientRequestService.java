package com.example.verylastapi.services;

import com.example.verylastapi.classes.requests.IngredientRequest;
import com.example.verylastapi.respositories.CocktailRequestRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class IngredientRequestService {
    final private CocktailRequestRespository cocktailRequestRespository;
    public void addNewIngredient(IngredientRequest ingredient, int id)
    {
        cocktailRequestRespository.findById(id).ifPresent((x)-> x.getIngredients().add(ingredient));

    }
}
