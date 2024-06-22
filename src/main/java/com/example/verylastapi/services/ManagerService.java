package com.example.verylastapi.services;

import com.example.verylastapi.classes.models.Cocktail;
import com.example.verylastapi.classes.models.Ingredient;
import com.example.verylastapi.classes.models.CocktailRequest;
import com.example.verylastapi.respositories.CocktailRequestRespository;
import com.example.verylastapi.respositories.CocktailRespository;
import com.example.verylastapi.respositories.IngredientRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
//add new ResponseEntities to all requests
@Service
@AllArgsConstructor
public class ManagerService {
    private final CocktailRespository cocktailRespository;
   private final CocktailRequestRespository cocktailRequestRespository;
   private final IngredientRespository ingredientRespository;
    public Cocktail acceptRequest(int id)//to test
    {
        CocktailRequest cocktailRequest= cocktailRequestRespository
                .findById(id)
                .orElse(null);
        if(cocktailRequest == null) throw new NoSuchElementException("CocktailRequest Not Found");
        Set<Ingredient> ingredients = new HashSet<>();
        Cocktail cocktail = Cocktail.builder()
                .name(cocktailRequest.getName())
                .description(cocktailRequest.getDescription())
                .imageUrl(cocktailRequest.getImageUrl())
                .prep(cocktailRequest.getPrep()).tag(cocktailRequest.getTag())
                .build();
        int cocktailId = (int) (cocktailRespository.count() + 1);
        cocktailRequest
                .getIngredients()
                .forEach((x) -> ingredients.add(new Ingredient(cocktailId, x.getName(), x.getQuantity(), x.getUnit())));
        ingredientRespository.saveAll(ingredients);
        cocktailRespository.save(cocktail);
        return cocktail;
    }

    public CocktailRequest rejectRequest(int id) ///to test
    {
        cocktailRequestRespository.findById(id)
                .ifPresentOrElse((x)->{
                    x.setInspected(true);
                    x.setDeleted(true);
                },()->{
                    throw new NoSuchElementException();
                });
        return cocktailRequestRespository.findById(id).get();
    }

    public List<CocktailRequest> getWaitingRequests()
    {
        return cocktailRequestRespository.findAllWaitingRequests();
    }

}
