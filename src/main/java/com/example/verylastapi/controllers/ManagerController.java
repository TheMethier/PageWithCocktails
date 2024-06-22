package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.models.Cocktail;
import com.example.verylastapi.classes.models.CocktailRequest;
import com.example.verylastapi.services.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    public ResponseEntity rejectRequest(@PathVariable("id") int id)
    {
        try {
            CocktailRequest cocktailRequest = service.rejectRequest(id);
            return ResponseEntity.ok(cocktailRequest);
        }
        catch(NoSuchElementException suchElementException)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PreAuthorize("hasAuthority('manager::read')")
    @GetMapping("requests")
    public ResponseEntity getWaitingRequests()
    {
        List<CocktailRequest> requests= service.getWaitingRequests();
        if(requests.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(service.getWaitingRequests());
    }


}
