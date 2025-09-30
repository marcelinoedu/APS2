package com.aps2ArqObj.APS2.Controllers;

import com.aps2ArqObj.APS2.Models.Token;
import com.aps2ArqObj.APS2.Services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<Token> login(@RequestBody Map<String, String> body) {
        String cpf = body.get("cpf");
        String senha = body.get("senha");

        try {
            Token token = authService.login(cpf, senha);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
    }
}
