package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.classes.requests.CocktailRequest;
import com.example.verylastapi.services.CocktailRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor

//remove my recipe
//add new recipe request
//get my recipes
//get my requests
public class CocktailRequestController {
    private final CocktailRequestService cocktailRequestService;
    @GetMapping ("{username}/requests/")
    public List<CocktailRequest> getMyRequests(@PathVariable String username)
    {
        return cocktailRequestService.getMyRequests(username);
    }
    @GetMapping("{username}/recipe/")
    public List<Cocktail> getMyRecipes(@PathVariable String username)
    {
        return cocktailRequestService.getMyCocktails(username);
    }
    @PostMapping("{username}/recipe/")
    public void addNewRecipe(@PathVariable String username, @RequestBody CocktailRequest cocktailRequest)
    {
        cocktailRequestService.addNewRecipe(username,cocktailRequest);
    }
    @DeleteMapping("{username}/recipe/{id}")
    public void deleteMyRecipe(@PathVariable("username") String username, @PathVariable("id") int id)
    {
         cocktailRequestService.deleteMyRecipe(username,id);
    }


}
