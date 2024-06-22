package com.example.verylastapi.services;

import com.example.verylastapi.classes.models.IngredientRequest;
import com.example.verylastapi.respositories.CocktailRequestRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
@AllArgsConstructor
public class IngredientRequestService {
    final private CocktailRequestRespository cocktailRequestRespository;
    public void addNewIngredient(IngredientRequest ingredient, int id)
    {
        cocktailRequestRespository
                .findById(id)
                .ifPresentOrElse(
                        (x)-> x.getIngredients().add(ingredient),
                        ()->{throw new NoSuchElementException("Not cocktail not found");}
                );
    }
}
