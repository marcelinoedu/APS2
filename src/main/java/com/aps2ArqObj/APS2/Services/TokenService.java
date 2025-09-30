package com.aps2ArqObj.APS2.Services;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class TokenService {
    private static final String CHAVE = "adgbiyeabvdyuabdybaeyidba8d87aedy8agdh8abd8aegbdg";
    private static final long EXPIRACAO = 1000 * 60 * 60;

    public String gerarToken(String cpf) {
        return Jwts.builder()
                .setSubject(cpf)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRACAO))
                .signWith(SignatureAlgorithm.HS256, CHAVE)
                .compact();
    }

    public String validarToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(CHAVE)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
