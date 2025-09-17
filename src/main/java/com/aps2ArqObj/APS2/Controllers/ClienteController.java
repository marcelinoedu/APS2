package com.aps2ArqObj.APS2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepo;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public Cliente criar(@RequestBody Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepo.findAll();
    }

    @GetMapping("/{cpf}")
    public Cliente buscarPorCpf(@PathVariable String cpf) {
        return clienteRepo.findByCpf(cpf).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Cliente editar(@PathVariable Long id, @RequestBody Cliente dados) {
        Cliente c = clienteRepo.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        c.setNome(dados.getNome());
        c.setEmail(dados.getEmail());
        return clienteRepo.save(c);
    }
}

