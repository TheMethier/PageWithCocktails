package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.classes.requests.CocktailRequest;
import com.example.verylastapi.services.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('MANAGER')")
@RestController
@RequestMapping("/api/v1/mod")
@RequiredArgsConstructor
//accept recipe (add cocktail to Cocktails)
//get all requests
//reject recipe

public class ManagerController {
    private final ManagerService service;
    @PreAuthorize("hasAuthority('manager::create')")
    @PostMapping("request/{id}")
    public ResponseEntity<Cocktail> acceptRequest(@PathVariable("id") int id)
    {

        Cocktail cocktail= service.acceptRequest(id);
        if(cocktail!=null)
            return ResponseEntity.ok(cocktail);
        return (ResponseEntity<Cocktail>) ResponseEntity.notFound();
    }
    @PreAuthorize("hasAuthority('manager::update')")
    @PutMapping("request/{id}")
    public CocktailRequest rejectRequest(@PathVariable("id") int id)
    {
       return service.rejectRequest(id);
    }
    @PreAuthorize("hasAuthority('manager::read')")
    @GetMapping("requests")
    public List<CocktailRequest> getWaitingRequests()
    {
        return service.getWaitingRequests();
    }
    @PreAuthorize("hasAuthority('manager::delete')")
    @DeleteMapping("requests/{id}")
    public void deleteCocktailFromRequest(@PathVariable("id") int id)
    {
       service.deleteCocktailFromRequest(id);
    }


}
