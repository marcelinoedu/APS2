// src/main/java/com/aps2ArqObj/APS2/Controllers/AuthController.java
package com.aps2ArqObj.APS2.Controllers;

import com.aps2ArqObj.APS2.DTO.LoginRequestDto;
import com.aps2ArqObj.APS2.DTO.TokenResponseDto;
import com.aps2ArqObj.APS2.Services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<TokenResponseDto> login(@Valid @RequestBody LoginRequestDto body) {
        try {
            TokenResponseDto token = authService.login(body.cpf(), body.senha());
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).build();
        }
    }
}
