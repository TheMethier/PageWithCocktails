package com.example.verylastapi.services;

import com.example.verylastapi.classes.models.Cocktail;
import com.example.verylastapi.classes.models.Token;
import com.example.verylastapi.classes.models.User;
import com.example.verylastapi.classes.models.CocktailRequest;
import com.example.verylastapi.respositories.CocktailRequestRespository;
import com.example.verylastapi.respositories.CocktailRespository;
import com.example.verylastapi.respositories.TokenRespository;
import com.example.verylastapi.respositories.UserRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor


public class CocktailRequestService {
    final private UserRespository userRespository;
    final private CocktailRequestRespository cocktailRequestRespository;
    final private TokenRespository tokenRespository;
    final private CocktailRespository cocktailRespository;
    public List<CocktailRequest> getMyRequests(String username) //to test
    {
        User user = userRespository.findByUsername(username)
                .orElse(null);
        if(user == null)
            throw new NoSuchElementException("User not found");
        return cocktailRequestRespository.findAllByUser_Id(user.getId());
    }
    public List<Cocktail> getMyCocktails(String username) //to test
    {
        User user=userRespository.findByUsername(username)
                .orElse(null);
        if(user == null)
        {
            throw new NoSuchElementException("User not found");
        }
        return cocktailRespository.findAllByByUser_Id(user.getId());
    }
    public void addNewRecipe(String username, Cocktail cocktail) {
        User user = userRespository.findByUsername(username)
                .orElse(null);
        if(user == null) {
        throw  new NoSuchElementException();
        }
        Token token = tokenRespository.findAllValidTokensFromUser(user.getId()).get(0);
        CocktailRequest cocktailRequest = CocktailRequest.builder()
                .name(cocktail.getName())
                .prep(cocktail.getPrep())
                .description(cocktail.getDescription())
                .ingredients(null)
                .isInspected(false)
                .user(user)
                .token(token)
                .imageUrl(cocktail.getImageUrl())
                .tag("")
                .build();
        cocktailRequestRespository.save(cocktailRequest);

    }

    public void deleteMyRequest(String username, int id) //to refactor and test handle exceptions in controllers
    {
        User user = userRespository.findByUsername(username)
                .orElse(null);
        if(user == null) throw  new NoSuchElementException("User not found");
        CocktailRequest cocktailRequest = cocktailRequestRespository.findById(id)
                .orElse(null);
        if(cocktailRequest == null) throw new NoSuchElementException("cocktailRequest not found");
        cocktailRequestRespository.delete(cocktailRequest);
        if(cocktailRequest.isAccepted())
        {
            cocktailRequest.setDeleted(true);
            cocktailRequestRespository.save(cocktailRequest);
        }
    }

    public CocktailRequest getMyRequest(String username, int id) //to refactor and test handle exception
    {
        User user = userRespository
                .findByUsername(username)
                .orElseThrow();
        if(user==null)
        {
            throw new NoSuchElementException("User not found");
        }
        CocktailRequest cocktailRequest=cocktailRequestRespository.findById(id)
                .orElse(null);
        if(cocktailRequest==null)
        {
            throw new NoSuchElementException("CocktailRequest not found");
        }
        return cocktailRequest;
    }
}
