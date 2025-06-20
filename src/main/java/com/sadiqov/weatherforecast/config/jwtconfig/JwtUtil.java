package com.sadiqov.weatherforecast.config.jwtconfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class JwtUtil {

    @Value("${SECRET_KEY}")
    String secretKey;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String getUserPin(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Date getDate(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return (!expiredToken(token) && (getUserPin(token).equals(userDetails.getUsername())));
    }

    private boolean expiredToken(String token) {
        return getDate(token).before(new Date());
    }
}

