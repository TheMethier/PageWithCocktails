package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.requests.IngredientRequest;
import com.example.verylastapi.services.IngredientRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/api/v1/user/indi")
//add ingredients to recipe
@RequiredArgsConstructor

public class IngredientRequestController {
    private final IngredientRequestService service;
    @PreAuthorize("hasAuthority('user::create')")
    @PostMapping("/{id}")
    public void addNewIngredient(@RequestBody() IngredientRequest Ingredients, @PathVariable("id") int id)
    {
        service.addNewIngredient(Ingredients,id);
    }

}
