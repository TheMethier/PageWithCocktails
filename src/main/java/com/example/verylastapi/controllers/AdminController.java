package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.classes.requests.CocktailRequest;
import com.example.verylastapi.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor

//accept recipe (add cocktail to Cocktails)
//get all requests
//reject recipe

public class AdminController {
    private final AdminService service;
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
}
