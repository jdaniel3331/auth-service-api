package com.jdaniel3331.authserviceapi.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtService {

    private String secretKey;
    private int expirationTimeMils;
    private String issuer;

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secretKey);
    }

    public String generateToken(UserDetails userDetails) {
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuer(issuer)
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + expirationTimeMils))
                .withClaim("roles", roles)
                .sign(getAlgorithm());
    }

    public boolean isTokenValid(String token) {
        try {
            verifyToken(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String extractUsername(String token){
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getSubject();
    }

    public List<GrantedAuthority> extractRoles(String token){
        DecodedJWT decodedJWT = verifyToken(token);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(decodedJWT.getClaim("roles").asList(String.class).stream()
                .map(SimpleGrantedAuthority::new)
                .toList());
        return authorities;
    }

    private DecodedJWT verifyToken(String token){
        return JWT.require(getAlgorithm())
                .withIssuer(issuer)
                .build()
                .verify(token);
    }

}
