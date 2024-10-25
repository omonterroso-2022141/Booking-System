package com.example.demo.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import com.example.demo.config.Jwt;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final Jwt jwt;

    public String generateToken(String username) {
        Key key = Keys.hmacShaKeyFor(jwt.getKey().getBytes());

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwt.getExpiration()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
