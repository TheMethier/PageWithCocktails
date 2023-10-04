package com.example.verylastapi.services;

import com.example.verylastapi.classes.*;
import com.example.verylastapi.classes.requests.AuthenticationRequest;
import com.example.verylastapi.classes.requests.RegisterRequest;
import com.example.verylastapi.classes.responses.AuthenticationResponse;
import com.example.verylastapi.enums.Role;
import com.example.verylastapi.enums.TokenType;
import com.example.verylastapi.respositories.TokenRespository;
import com.example.verylastapi.respositories.UserRespository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private final UserRespository userRespository;
    private final TokenRespository tokenRespository;
    private final PasswordEncoder encoder;
    private final JwtService service;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request)
    {
        User user= User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .username(request.getUsername())
                .role(Role.USER)
                .build();
        userRespository.save(user);
        String jwt=service.generateToken(user);
        Token token= Token.builder()
                .tokenType(TokenType.Bearer)
                .token(jwt).user(user)
                .isRevoked(false)
                .isExpired(false)
                .build();
        tokenRespository.save(token);
        return AuthenticationResponse.builder().token(jwt).build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        User user= userRespository.findByUsername(request.getUsername()).orElseThrow();   String jwt=service.generateToken(user);
       revokeAllUserTokens(user);
       Token token= Token.builder()
                .tokenType(TokenType.Bearer)
                .token(jwt)
       .user(user)
                .isRevoked(false)
                .isExpired(false)
                .build();
        tokenRespository.save(token);
        return  AuthenticationResponse.builder().token(jwt).build();
    }
    public void revokeAllUserTokens(User user)
    {
        List<Token> valid=tokenRespository.findAllValidTokensFromUser(user.getId());
        if(valid.isEmpty())
        {return;
       }
       valid.forEach(token -> {token.setRevoked(true); token.setExpired(true);});
        tokenRespository.saveAll(valid);
   }
}