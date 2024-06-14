package com.example.verylastapi.services;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.classes.Ingredient;
import com.example.verylastapi.classes.requests.CocktailRequest;
import com.example.verylastapi.respositories.CocktailRequestRespository;
import com.example.verylastapi.respositories.CocktailRespository;
import com.example.verylastapi.respositories.IngredientRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
//add new ResponseEntities to all requests
@Service
@AllArgsConstructor
public class ManagerService {
    private final CocktailRespository cocktailRespository;
   private final CocktailRequestRespository cocktailRequestRespository;
   private final IngredientRespository ingredientRespository;
    public Cocktail acceptRequest(int id) {
      CocktailRequest cocktailRequest= cocktailRequestRespository.findById(id).get();
        Set<Ingredient> ingredients = new HashSet<>();
        Cocktail cocktail = new Cocktail(cocktailRequest.getName(), cocktailRequest.getDescription(), cocktailRequest.getImageUrl(),  cocktailRequest.getPrep(), cocktailRequest.getTag());
        int cocktailId = cocktailRespository.findAll().size() + 1;
        cocktailRequest.getIngredients().forEach((x) -> ingredients.add(new Ingredient(cocktailId, x.getName(), x.getQuantity(), x.getUnit())));
        ingredientRespository.saveAll(ingredients);
        cocktailRespository.save(cocktail);
        return cocktail;
    }

    public CocktailRequest rejectRequest(int id) {
        cocktailRequestRespository.findById(id).ifPresent((x)->x.setInspected(true));
        return cocktailRequestRespository.findById(id).get();
    }

    public List<CocktailRequest> getWaitingRequests()
    {
         return cocktailRequestRespository.findAllWaitingRequests();
    }
    public void deleteCocktailFromRequest(int id) {
        cocktailRespository.delete(cocktailRespository
                .findCocktailByName(cocktailRequestRespository.findById(id).get().getName()));
        cocktailRequestRespository.deleteById(id);
    }
}
