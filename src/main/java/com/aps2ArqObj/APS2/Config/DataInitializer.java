package com.aps2ArqObj.APS2.Config;

import com.aps2ArqObj.APS2.Models.Cliente;
import com.aps2ArqObj.APS2.Services.ClienteService;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;

@Configuration
public class DataInitializer {

    private final ClienteService clienteService;

    public DataInitializer(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostConstruct
    public void init() {

        Cliente cliente = new Cliente(
                "12345678900",
                "Usuário Admin",
                LocalDate.of(1990, 1, 1),
                5000.0f,
                "admin@email.com",
                "1234"
        );

        clienteService.cadastrarCliente(cliente);
        System.out.println("Cliente inicial criado: CPF 12345678900 / Senha 1234");
    }
}
