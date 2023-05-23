package com.example.verylastapi.services;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.respositories.CocktailRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocktailService {
    private final CocktailRespository respository;
    @Autowired
    public CocktailService(CocktailRespository respository)
    {
        this.respository=respository;
    }

    public List<Cocktail> getCocktails()
    {
        return respository.findAll();
    }
    public void addNewCocktail(Cocktail cocktail)
    {
        respository.save(cocktail);
    }
    public void deleteCocktail(Long Id)
    {
        boolean exist=respository.existsById(Id);
        if(exist)
        {
            respository.deleteById(Id);
        }
        else
        {
            throw new IllegalStateException("Cocktail doesn't exist");

        }
    }
//update
}
