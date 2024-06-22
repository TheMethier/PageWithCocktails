package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.requests.CocktailAdditionRequest;
import com.example.verylastapi.services.CocktailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CocktailService service;
    @PreAuthorize("hasAuthority('admin::create')")
    @PostMapping()
    public CocktailAdditionRequest createNewCocktail(@RequestBody @Valid CocktailAdditionRequest cocktail)
    {
        service.addNewCocktail(cocktail);
        return cocktail;
    }
    @PreAuthorize("hasAuthority('admin::delete')")
    @DeleteMapping("/{id}")
    public void deleteCocktail(@PathVariable("id") Long id)
    {
        service.deleteCocktail(id);
    }

}
