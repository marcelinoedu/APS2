package com.aps2ArqObj.APS2.Services;

import com.aps2ArqObj.APS2.Models.Cliente;
import com.aps2ArqObj.APS2.Repositories.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clienteRepository.findById(cpf);
    }

    @Transactional
    public Cliente editarCliente(String cpf, Cliente atualizado) {
        if (!clienteRepository.existsById(cpf)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        atualizado.setCpf(cpf);
        return clienteRepository.save(atualizado);
    }
}
