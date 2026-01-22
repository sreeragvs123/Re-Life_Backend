package com.example.Relife_backend.services;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

import com.example.Relife_backend.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));//Converts your secret string into a cryptographic key .
    }

    public String generateRefreshToken(UserEntity user) {
        return Jwts.builder()
                .subject(user.getId().toString())//Usually used to uniquely identify the user
                .issuedAt(new Date())//Token creation time
                .expiration(new Date(System.currentTimeMillis()+ 1000L *60*60*24*30*12))//Token expiration time (1 minute here)
                .signWith(getSigningKey())//Signing the token with the secret key
                .compact();//Building the token
    }


    public String generateAccessToken(UserEntity user) {
        return Jwts.builder()
                .subject(user.getId().toString())//Usually used to uniquely identify the user
                .claim("username", user.getEmail())//Payload data
                .claim("roles", user.getRole())//Payload data
                .issuedAt(new Date())//Token creation time
                .expiration(new Date(System.currentTimeMillis()+1000*60))//Token expiration time (1 minute here)
                .signWith(getSigningKey())//Signing the token with the secret key which is protected cryptographically
                .compact();//Building the token
    }

    public Long getUserIdFromToken(String jwtToken) {
        Claims claims = Jwts.parser()//Creates a JWT parser builder , This parser will be used to read and validate JWTs
                .verifyWith(getSigningKey())//Tells the parser how to verify the JWT signature
                .build()//Produces an immutable, thread-safe JwtParser
                .parseSignedClaims(jwtToken)// spits the token and verifies its signature and expirationn dates, header, payload and return jws Object
                .getPayload();//xtracts the payload (claims) from the verified token

        return Long.valueOf(claims.getSubject());
    }
}
