// src/main/java/com/aps2ArqObj/APS2/Config/DataInitializer.java
package com.aps2ArqObj.APS2.Config;

import com.aps2ArqObj.APS2.Models.Cartao;
import com.aps2ArqObj.APS2.Models.Cliente;
import com.aps2ArqObj.APS2.Models.ContaCorrente;
import com.aps2ArqObj.APS2.Services.CartaoService;
import com.aps2ArqObj.APS2.Services.ClienteService;
import com.aps2ArqObj.APS2.Services.ContaService;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Configuration
public class DataInitializer {

    private final ClienteService clienteService;
    private final ContaService contaService;
    private final CartaoService cartaoService;

    public DataInitializer(ClienteService clienteService,
                           ContaService contaService,
                           CartaoService cartaoService) {
        this.clienteService = clienteService;
        this.contaService = contaService;
        this.cartaoService = cartaoService;
    }

    @PostConstruct
    public void init() {
        final String cpfAdmin = "12345678900";

        Optional<Cliente> existing = clienteService.buscarPorCpf(cpfAdmin);
        if (existing.isPresent()) {
            System.out.println("Cliente inicial já existe: CPF " + cpfAdmin);
            return;
        }

        ContaCorrente conta = new ContaCorrente(
                "0001",
                "00000001",
                1000.0f,
                500.0f
        );

        Cliente cliente = new Cliente(
                cpfAdmin,
                "Usuário Admin",
                LocalDate.of(1990, 1, 1),
                5000.0f,
                "admin@email.com",
                "1234"
        );
        cliente.setConta(conta);
        clienteService.cadastrarCliente(cliente);

        Cartao cartao = new Cartao(
                UUID.randomUUID().toString(),
                "DEBITO",
                LocalDate.now().plusYears(4)
        );
        cartaoService.emitirCartao(conta, cartao);

        System.out.println("Cliente inicial criado: CPF " + cpfAdmin + " / Senha 1234");
        System.out.println("Conta criada: agência " + conta.getAgencia() + " / número " + conta.getNumero());
        System.out.println("Cartão emitido: número " + cartao.getNumeroCartao() + " (tipo " + cartao.getTipo() + ")");
    }
}
