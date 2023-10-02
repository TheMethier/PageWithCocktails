package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.AuthenticationRequest;
import com.example.verylastapi.classes.AuthenticationResponse;
import com.example.verylastapi.classes.RegisterRequest;
import com.example.verylastapi.services.AuthenticationService;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequiredArgsConstructor

@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping("register")
    public AuthenticationResponse register(@RequestBody RegisterRequest request)
    {
        return service.register(request);
    }
    @PostMapping("authenticate")
    public AuthenticationResponse authenticate (@RequestBody AuthenticationRequest request)
    {
        return service.authenticate(request);
    }

}
