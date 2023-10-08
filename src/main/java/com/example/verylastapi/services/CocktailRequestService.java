package com.example.verylastapi.services;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.classes.Token;
import com.example.verylastapi.classes.User;
import com.example.verylastapi.classes.requests.CocktailRequest;
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
    public List<CocktailRequest> getMyRequests(String username)
    {
        User user=userRespository.findByUsername(username).orElse(null);
        if(user!=null)
        {
                return cocktailRequestRespository.findAllByUser_Id(user.getId());
        }
        return null;
    }
    public List<Cocktail> getMyCocktails(String username)
    {
        User user=userRespository.findByUsername(username).orElse(null);
        if(user!=null)
        {
            return cocktailRespository.findAllByByUser_Id(user.getId());
        }
        return null;
    }
    public void addNewRecipe(String username, Cocktail cocktail) {
        User user=userRespository.findByUsername(username).orElse(null);
        if(user!=null) {
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
        return ;
    }

    public void deleteMyRequest(String username, int id) {
        User user=userRespository.findByUsername(username).orElse(null);
        if(user!=null)
        {
            CocktailRequest cocktailRequest=cocktailRequestRespository.findById(id).orElse(null);
            if(cocktailRequest!=null)
            {
             cocktailRequestRespository.delete(cocktailRequest);
            if(cocktailRequest.isAccepted())
            {
                cocktailRequest.setDeleted(true);
                cocktailRequestRespository.save(cocktailRequest);
            }
            }
        }
    }
}
