package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.Ingredient;
import com.example.verylastapi.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Ingredient> GetIndiById(@PathVariable("id") Long Id){
        return  service.GetAllIngredients(Id);
    }


    @PostMapping("/{id}")
    public void addNewIngredients(@RequestBody(required = true) Ingredient Ingredients, @PathVariable("id") int id)
    {
        service.addNewIngredients(Ingredients,id);
    }
}
