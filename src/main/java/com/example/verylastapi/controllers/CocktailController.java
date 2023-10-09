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

    @GetMapping("/cocktail/")
    public List<Cocktail> getCocktails()
    {
        return service.getCocktails();
    }

    @GetMapping("/cocktail/{tag}")

            public List<Cocktail> getCocktailByTag(@PathVariable ("tag") String tag)
    {
        System.out.println(tag);
        return service.getCocktailByTag(tag);
    }

    @GetMapping("/{id}")
    public Optional<Cocktail> getCocktailById(@PathVariable ("id") Long Id){
    return  service.getCocktail(Id);
    }

    @PostMapping("/")
    public void addNewCocktail(@RequestBody Cocktail cocktail)
    {
        service.addNewCocktail(cocktail);
    }

    @DeleteMapping(path="/{id}")
    public void removeCocktail(@PathVariable ("id") Long Id)
    {
        service.deleteCocktail(Id);
    }
}
