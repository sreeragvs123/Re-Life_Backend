package com.example.Relife_backend.services;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import com.example.Relife_backend.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // ── Access Token — 6 months (college app, simple & practical) ─────────────
    // FIX: was 1000*60 = 1 minute → now 6 months
    // 1000L * 60 * 60 * 24 * 30 * 6 = ~180 days
    public String generateAccessToken(UserEntity user) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("username", user.getEmail())
                .claim("roles", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30 * 6))
                .signWith(getSigningKey())
                .compact();
    }

    // ── Refresh Token — 1 year ─────────────────────────────────────────────────
    public String generateRefreshToken(UserEntity user) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365))
                .signWith(getSigningKey())
                .compact();
    }

    public Long getUserIdFromToken(String jwtToken) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }
}