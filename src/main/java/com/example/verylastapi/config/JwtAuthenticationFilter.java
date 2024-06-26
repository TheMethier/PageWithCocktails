package com.example.verylastapi.config;

import com.example.verylastapi.classes.models.Token;
import com.example.verylastapi.respositories.TokenRespository;
import com.example.verylastapi.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Configuration
@RequiredArgsConstructor
@Profile("!test")

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    final private JwtService service;
    final private UserDetailsService userDetailsService;
    final private TokenRespository tokenRespository;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException
    {
        String authHeader=request.getHeader("Authorization");
        String jwt;
        String username;
        //check if Token exist or is missing (without Bearer) we skip this request and respond
        if((authHeader==null) ||(!authHeader.startsWith("Bearer ")))
        {
            filterChain.doFilter(request,response);
            return;
        }
        jwt=authHeader.substring(7);
        username=service.extractUsername(jwt);
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)//check if user was authenticated before and if username is not null
        {
            UserDetails userDetails= userDetailsService.loadUserByUsername(username);//get user as user details
            if(service.isTokenValid(jwt,userDetails))//checks if encrypted username in token equals username from database and if token non expired
            {
                Token token=tokenRespository.findByToken(jwt).orElse(null);
                if(token!=null&&((!token.isRevoked()) && (!token.isExpired()))) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);//set authentication in contextHolder to make sure that username is authenticates
                }
            }
        }
        filterChain.doFilter(request,response); //skip request and respond
    }
}
