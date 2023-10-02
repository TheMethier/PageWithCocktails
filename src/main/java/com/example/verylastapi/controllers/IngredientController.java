package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.classes.Ingredients;
import com.example.verylastapi.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<Ingredients> GetIndiById(@PathVariable("id") Long Id){
        return  service.GetAllIngredients(Id);
    }


    @PostMapping("/{id}")
    public void addNewIngredients(@RequestBody(required = true) Ingredients Ingredients,@PathVariable("id") int id)
    {
        service.addNewIngredients(Ingredients,id);
    }
}
