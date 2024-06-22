package com.example.verylastapi.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {
    private  final  String secretKey="7341db6f21e6b1e663bab5dc95c88095422c978f6754ede1caac3c5976f830880c6becaf884398f51dc18d2b77a078fafbd3dd2dab824b03dd7c239b68454e1bbc6ee1bf9563176459ff3a878545e028f22dd027d92a9e4fdafbd2b1bb563441d1b5ff821ca2328ebbbf871e0c1fd79e9e8d646c486cfd23a50e0ef7a37faa92";
    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }
    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver)
    //<T> - generic type, T - return value type
    // Function<A,B>:  A - argument type, B - return value type
    // We passing "claimsResolver" as second argument to make function more universal and get exact claims that we want. (It is lamda )
    //todo read about lamda expressions and generic types from java.utils
    {
        final Claims claims=extractClaims(jwtToken);
        return claimsResolver.apply(claims);
    }
    public Claims extractClaims(String jwtToken)
    {
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .parseClaimsJws(jwtToken)
                .getBody();
    }
    public String generateToken(UserDetails userDetails)
    {
        return generateToken(new HashMap<>(),userDetails);
    }
    public Boolean isTokenValid(String jwtToken, UserDetails userDetails)
    {
        String username=extractUsername(jwtToken);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
    }
    public Boolean isTokenExpired(String jwtToken)
    {
        Date date= extractClaim(jwtToken,Claims::getExpiration);
        return date.before(new Date());
    }
    public Key getSignInKey()
    {
        //todo create encrypted key generator
        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails)
    {
        Key key = this.getSignInKey();
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))//Expiration of token - 1 day
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

}
