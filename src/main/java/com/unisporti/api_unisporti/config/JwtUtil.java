package com.unisporti.api_unisporti.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET = "cGF0by1wYXRvLWdhbnNvLXBhdG8tcGF0by1nYW5zby1wYXRvLXBhdG8tZ2Fuc28=";
    private final SecretKey SECRET_KEY;

    private static final int expirationSeconds = 3600;

    public JwtUtil() {
        this.SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));
    }

    public String generateToken(String fullName, String cpf, Integer userId, Character role) {
        return Jwts.builder().subject(fullName).claim("cpf", cpf).claim("idUser", userId).claim("role", role).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000)).signWith(SECRET_KEY).compact();
    }

    public String extractCpf(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return getClaimsFromToken(token).getExpiration().before(new Date());
    }

    public Claims getClaimsFromToken(String token) {
        final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getEncoded());

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }
}
