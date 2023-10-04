package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.requests.IngredientRequest;
import com.example.verylastapi.services.IngredientRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/v1/user/indi")
//add ingredients to recipe
@RequiredArgsConstructor

public class IngredientRequestController {
    private final IngredientRequestService service;
    @PostMapping("/{id}")
    public void addNewIngredients(@RequestBody(required = true) IngredientRequest Ingredients, @PathVariable("id") int id)
    {
        service.addNewIngredients(Ingredients,id);
    }

}
