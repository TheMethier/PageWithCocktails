package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.requests.AuthenticationRequest;
import com.example.verylastapi.classes.responses.AuthenticationResponse;
import com.example.verylastapi.classes.requests.RegisterRequest;
import com.example.verylastapi.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
