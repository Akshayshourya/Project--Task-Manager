package com.akshay.taskmanager.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String secret = "khul Jaa Sim Sim! nai to Band ho ja Sim Sim!";
    private static final long expirationTime = 1000 * 60 * 60;

    private static final Key key = Keys.hmacShaKeyFor(secret.getBytes());

    public static String generateToken(Long userid, String email){
        return Jwts.builder()
                .setSubject(email)
                .claim("userId",userid)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims validateToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
