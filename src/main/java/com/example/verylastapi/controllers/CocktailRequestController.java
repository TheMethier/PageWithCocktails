package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.models.Cocktail;
import com.example.verylastapi.classes.models.CocktailRequest;
import com.example.verylastapi.services.CocktailRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor

public class CocktailRequestController {
    private final CocktailRequestService cocktailRequestService;
    @PreAuthorize("hasAuthority('user::read')")
    @GetMapping ("{username}/requests/")
    public ResponseEntity<List<CocktailRequest>> getMyRequests(@PathVariable String username)
    {
        return ResponseEntity.ok(cocktailRequestService.getMyRequests(username));
    }
    @PreAuthorize("hasAuthority('user::read')")
    @GetMapping("{username}/recipe/")
    public ResponseEntity<List<Cocktail>> getMyRecipes(@PathVariable String username)
    {
        List<Cocktail> cocktailRequests;
        try {
           cocktailRequests = cocktailRequestService.getMyCocktails(username);
            if(cocktailRequests.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(cocktailRequests);
        }
        catch (NoSuchElementException noSuchElementException)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PreAuthorize("hasAuthority('user::read')")
    @GetMapping("{username}/request/{id}")
    public ResponseEntity<CocktailRequest> getMyRequest (@PathVariable("username") String username, @PathVariable("id") int id)
    {
        CocktailRequest cocktailRequest;
        try {
            cocktailRequest=cocktailRequestService.getMyRequest(username, id);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).build();
        }
        return ResponseEntity.ok(cocktailRequest);
    }
    @PreAuthorize("hasAuthority('user::create')")
    @PostMapping("{username}/recipe/")
    public ResponseEntity addNewRecipe(@PathVariable String username, @RequestBody Cocktail cocktailRequest)
    {
        try {
            cocktailRequestService.addNewRecipe(username,cocktailRequest);
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();

    }
    @PreAuthorize("hasAuthority('user::delete')")
    @DeleteMapping("{username}/request/{id}")
    public ResponseEntity deleteMyRequest(@PathVariable("username") String username, @PathVariable("id") int id)
    {
        try {
            cocktailRequestService.deleteMyRequest(username,id);
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
