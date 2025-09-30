package com.aps2ArqObj.APS2.Services;

import com.aps2ArqObj.APS2.Models.Cliente;
import com.aps2ArqObj.APS2.Models.Token;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final TokenService tokenService;
    private final ClienteService clienteService;

    public AuthService(TokenService tokenService, ClienteService clienteService) {
        this.tokenService = tokenService;
        this.clienteService = clienteService;
    }


    public Token login(String cpf, String senha) {
        return clienteService.buscarPorCpf(cpf)
                .filter(c -> senha.equals(c.getSenha()))
                .map(c -> new Token(tokenService.gerarToken(c.getCpf())))
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
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
