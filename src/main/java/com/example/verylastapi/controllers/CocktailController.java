package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.models.Cocktail;
import com.example.verylastapi.classes.requests.CocktailAdditionRequest;
import com.example.verylastapi.services.CocktailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Cocktail>> getCocktails()
    {
        List<Cocktail> cocktails = service.getCocktails();
        if(cocktails.isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .build();
        }
        return ResponseEntity.ok(service.getCocktails());
    }

    @GetMapping("/cocktail/{tag}")
    public ResponseEntity<List<Cocktail>> getCocktailByTag(@PathVariable ("tag") String tag)
    {
        List<Cocktail> cocktails = service.getCocktailByTag(tag);
        if(cocktails.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cocktails);
        }
        return ResponseEntity.ok(cocktails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cocktail> getCocktailById(@PathVariable ("id") Long id)
    {
        Cocktail cocktail = service.getCocktail(id);
        if(cocktail == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON).build();
        }
        return ResponseEntity.ok(cocktail);
    }

    @PostMapping("/")
    public ResponseEntity addNewCocktail(@RequestBody @Valid CocktailAdditionRequest cocktail)
    {

        service.addNewCocktail(cocktail);
        return ResponseEntity.status(HttpStatus.CREATED).body(cocktail);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity removeCocktail(@PathVariable ("id") Long id)
    {
        try {
            service.deleteCocktail(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (IllegalStateException exception)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Cocktail with "+id.toString() + " doesn't exist");
        }
    }
}
