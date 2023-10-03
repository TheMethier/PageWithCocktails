package com.example.verylastapi.services;

import com.example.verylastapi.classes.Token;
import com.example.verylastapi.respositories.TokenRespository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class LogoutService implements LogoutHandler
{
 final private TokenRespository respository;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader=request.getHeader("Authorization");
        String jwt;
        //check if Token exist or is missing (without Bearer) we skip this request and respond
        if((authHeader==null) ||(!authHeader.startsWith("Bearer ")))
        {
            return;
        }
        jwt=authHeader.substring(7);
        Token token=respository.findByToken(jwt).orElse(null);
        if(token!=null)
        {
            token.setExpired(true);
            token.setRevoked(true);
            respository.save(token);
        }
    }
}
