package com.aps2ArqObj.APS2.Services;
import com.aps2ArqObj.APS2.Models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final List<User> usuarios = new ArrayList<>();

    private final String secretKey = "eakdbiuaebduibaeuidbuaduhauidhuihaduheuiheauidhuehbfbiusfbrsbfurbsu";

    private final long expirationTime = 1000 * 60 * 60;

    public String register(User user) {
        Optional<User> existente = usuarios.stream()
                .filter(u -> u.getEmail().equals(user.getEmail()))
                .findFirst();
        if (existente.isPresent()) {
            throw new RuntimeException("Usuário já cadastrado com este email!");
        }

        usuarios.add(user);
        return generateToken(user);
    }

    public String login(String email, String senha) {
        Optional<User> usuario = usuarios.stream()
                .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                .findFirst();

        if (usuario.isEmpty()) {
            throw new RuntimeException("Credenciais inválidas!");
        }

        return generateToken(usuario.get());
    }

    private String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("nome", user.getNome())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

}

