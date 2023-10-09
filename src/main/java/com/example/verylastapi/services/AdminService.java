package com.example.verylastapi.services;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.respositories.CocktailRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class AdminService {
    private final CocktailRespository cocktailRespository;
    public Cocktail createNewCocktail(Cocktail cocktail)
    {
         cocktailRespository.save(cocktail);
         return cocktail;
    }

    public void deleteCocktail(Long id) {
      cocktailRespository.findById(id).ifPresent(cocktailRespository::delete);
    }
}
