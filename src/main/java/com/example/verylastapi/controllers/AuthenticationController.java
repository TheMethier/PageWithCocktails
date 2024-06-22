package com.example.verylastapi.controllers;

import com.example.verylastapi.classes.requests.AuthenticationRequest;
import com.example.verylastapi.classes.responses.AuthenticationResponse;
import com.example.verylastapi.classes.requests.RegisterRequest;
import com.example.verylastapi.services.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request)
    {
        return ResponseEntity.ok( service.register(request));
    }
    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody @Valid AuthenticationRequest request)
    {
        AuthenticationResponse response = service.authenticate(request);
        HttpCookie cookie = ResponseCookie.from("token", response.getToken())
                .path("/")
                .maxAge(86400)
                .secure(true)
                .httpOnly(false)
                .path("/")
                .sameSite("None")
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,
                        cookie.toString(),HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true",
                        HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:3000")
                        .body(response);
    }

}
