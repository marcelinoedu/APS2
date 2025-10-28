// src/main/java/com/aps2ArqObj/APS2/Services/AuthService.java
package com.aps2ArqObj.APS2.Services;

import com.aps2ArqObj.APS2.DTO.TokenResponseDto;
import com.aps2ArqObj.APS2.Services.TokenService;
import com.aps2ArqObj.APS2.Models.Cliente;
import com.aps2ArqObj.APS2.Services.ClienteService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final TokenService tokenService;
    private final ClienteService clienteService;

    public AuthService(TokenService tokenService, ClienteService clienteService) {
        this.tokenService = tokenService;
        this.clienteService = clienteService;
    }

    public TokenResponseDto login(String cpf, String senha) {
        Cliente cliente = clienteService.buscarPorCpf(cpf)
                .filter(c -> senha.equals(c.getSenha()))
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        return tokenService.gerarTokenResponse(cliente.getCpf());
    }

    public boolean validar(String token) {
        try {
            tokenService.validarToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
