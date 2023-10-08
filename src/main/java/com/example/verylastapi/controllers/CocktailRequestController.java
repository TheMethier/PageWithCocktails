package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.classes.requests.CocktailRequest;
import com.example.verylastapi.services.CocktailRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CocktailRequest>> getMyRequests(@PathVariable String username)
    {
        return ResponseEntity.ok(cocktailRequestService.getMyRequests(username));
    }
    @GetMapping("{username}/recipe/")
    public List<Cocktail> getMyRecipes(@PathVariable String username)
    {
        return cocktailRequestService.getMyCocktails(username);
    }
    @PostMapping("{username}/recipe/")
    public void addNewRecipe(@PathVariable String username, @RequestBody Cocktail cocktailRequest)
    {
        cocktailRequestService.addNewRecipe(username,cocktailRequest);
    }
    @DeleteMapping("{username}/request/{id}")
    public void deleteMyRequest(@PathVariable("username") String username, @PathVariable("id") int id)
    {
         cocktailRequestService.deleteMyRequest(username,id);
    }


}
