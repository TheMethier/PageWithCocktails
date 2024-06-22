package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.models.Ingredient;
import com.example.verylastapi.classes.requests.IngredientAdditionRequest;
import com.example.verylastapi.services.IngredientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/indi")
@CrossOrigin(origins = "*")
public class IngredientController {
    private final IngredientService service;
    @Autowired
    public IngredientController(IngredientService service) {
        this.service = service;
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Ingredient>> getIngredientById(@PathVariable("id") Long Id)
    {
        List<Ingredient> ingredients = service.GetAllIngredients(Id);
        if(ingredients.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return  ResponseEntity.ok(ingredients);
    }
    @PostMapping("/{id}")
    public ResponseEntity<IngredientAdditionRequest> addNewIngredients(@RequestBody @Valid IngredientAdditionRequest ingredient,
                                                                       @PathVariable("id") Long id)
    {
        try {
            service.addNewIngredient(ingredient, id);
        }
        catch (NoSuchElementException NoSuchElementException)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredient);
    }
}
