package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService service;
    @PostMapping()
    public Cocktail createNewCocktail(@RequestBody Cocktail cocktail)
    {
        return service.createNewCocktail(cocktail);
    }

    @DeleteMapping ()
    public void deleteCocktail(Long id)
    {

        service.deleteCocktail(id);
    }

}
