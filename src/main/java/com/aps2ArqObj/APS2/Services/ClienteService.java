package com.aps2ArqObj.APS2.Services;



import com.aps2ArqObj.APS2.Models.Cliente;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ClienteService {
    private final Map<String, Cliente> clientes = new HashMap<>();

    public Cliente cadastrarCliente(Cliente cliente) {
        clientes.put(cliente.getCpf(), cliente);
        return cliente;
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes.values());
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        return Optional.ofNullable(clientes.get(cpf));
    }

    public Cliente editarCliente(String cpf, Cliente atualizado) {
        if (clientes.containsKey(cpf)) {
            clientes.put(cpf, atualizado);
            return atualizado;
        }
        throw new RuntimeException("Cliente não encontrado");
    }
}
