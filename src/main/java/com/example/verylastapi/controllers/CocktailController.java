package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.services.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cocktails")
@CrossOrigin(origins = "*")

public class CocktailController {
private final CocktailService service;

@Autowired
    public CocktailController(CocktailService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Cocktail> GetCocktails()
    {
        return service.getCocktails();
    }

    @GetMapping("/{id}")
    public Optional<Cocktail> GetCoctailById(@PathVariable ("id") Long Id){
    return  service.getCocktail(Id);
    }
    @PostMapping("/")
    public void AddNewCocktail(@RequestBody Cocktail cocktail)
    {

        service.addNewCocktail(cocktail);
    }
    @DeleteMapping(path="/{id}")
    public void RemoveCocktail(@PathVariable ("id") Long Id)
    {
        service.deleteCocktail(Id);
    }
};
