package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.classes.requests.CocktailRequest;
import com.example.verylastapi.services.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mod")
@RequiredArgsConstructor
//accept recipe (add cocktail to Cocktails)
//get all requests
//reject recipe

public class ManagerController {
    private final ManagerService service;
    @PostMapping("request/{id}")
    public Cocktail acceptRequest(@PathVariable("id") int id)
    {
        return service.acceptRequest(id);
    }
    @PutMapping("request/{id}")
    public CocktailRequest rejectRequest(@PathVariable("id") int id)
    {
       return service.rejectRequest(id);
    }
    @GetMapping("requests")
    public List<CocktailRequest> getWaitingRequests()
    {
        return service.getWaitingRequests();
    }
    @DeleteMapping("requests/{id}")
    public void deleteCocktailFromRequest(@PathVariable("id") int id)
    {
       service.deleteCocktailFromRequest(id);
    }


}
