// src/main/java/com/aps2ArqObj/APS2/Services/TokenService.java
package com.aps2ArqObj.APS2.Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.security.Key;

import com.aps2ArqObj.APS2.DTO.TokenResponseDto;

@Service
public class TokenService {
    private static final String CHAVE = "adgbiyeabvdyuabdybaeyidba8d87aedy8agdh8abd8aegbdg";
    private static final long EXPIRACAO_MS = 1000 * 60 * 60; // 1 hora
    private final Key signingKey;

    public TokenService() {
        this.signingKey = Keys.hmacShaKeyFor(CHAVE.getBytes());
    }

    public String gerarToken(String cpf) {
        return Jwts.builder()
                .setSubject(cpf)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRACAO_MS))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public TokenResponseDto gerarTokenResponse(String cpf) {
        String token = gerarToken(cpf);
        Instant expiresAt = Instant.now().plusMillis(EXPIRACAO_MS);
        return new TokenResponseDto(token, expiresAt);
    }

    public String validarToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
