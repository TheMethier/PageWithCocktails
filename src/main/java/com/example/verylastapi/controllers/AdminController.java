package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService service;
    @PreAuthorize("hasAuthority('admin::create')")
    @PostMapping()
    public Cocktail createNewCocktail(@RequestBody Cocktail cocktail)
    {
        return service.createNewCocktail(cocktail);
    }
    @PreAuthorize("hasAuthority('admin::delete')")
    @DeleteMapping("/{id}")
    public void deleteCocktail(@PathVariable("id") Long id)
    {
        service.deleteCocktail(id);
    }

}
