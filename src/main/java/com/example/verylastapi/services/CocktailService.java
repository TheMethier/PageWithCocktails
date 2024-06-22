package com.example.verylastapi.services;

import com.example.verylastapi.classes.models.Cocktail;
import com.example.verylastapi.classes.models.Ingredient;
import com.example.verylastapi.classes.requests.CocktailAdditionRequest;
import com.example.verylastapi.respositories.CocktailRespository;
import com.example.verylastapi.respositories.IngredientRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocktailService {

    private final CocktailRespository respository;
    private final IngredientRespository ingredientRespository;
    @Autowired
    public CocktailService(CocktailRespository respository, IngredientRespository respository1)
    {
        this.respository=respository;
        this.ingredientRespository =respository1;
    }

    public List<Cocktail> getCocktails()
    {
        return respository.findAll();
    }
    public Cocktail getCocktail(Long id)
    {

        return respository.findById(id).orElse(null);
    }
    public void addNewCocktail(CocktailAdditionRequest cocktail)
    {
        Cocktail newCocktail = Cocktail.builder()
                .name(cocktail.getName())
                .description(cocktail.getDescription())
                .prep(cocktail.getPrep())
                .imageUrl(cocktail.getImageUrl())
                .tag(cocktail.getTag())
                .build();
        respository.save(newCocktail);
    }
    public void deleteCocktail(Long Id)//to test
    {
        Cocktail cocktail = respository.findById(Id).orElse(null);
        if(cocktail==null) throw new IllegalStateException();
        List<Ingredient> ingredients=ingredientRespository.findByCocktailId(Id);
        ingredientRespository.deleteAllInBatch(ingredients);
        respository.delete(cocktail);

    }

    public List<Cocktail> getCocktailByTag(String tag) {
        StringBuilder formattedTag= new StringBuilder("%");
        for (int i=0;i<tag.length();i++){
            formattedTag.append(tag.charAt(i)).append("%");
        }
        return respository.getByTag(formattedTag.toString());
    }


}
