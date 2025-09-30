package com.aps2ArqObj.APS2.Controllers;

import com.aps2ArqObj.APS2.Models.Cliente;
import com.aps2ArqObj.APS2.Security.Autenticado;
import com.aps2ArqObj.APS2.Services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @Autenticado
    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.cadastrarCliente(cliente));
    }


    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }


    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> buscar(@PathVariable String cpf) {
        return clienteService.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Autenticado
    @PutMapping("/{cpf}")
    public ResponseEntity<Cliente> editar(@PathVariable String cpf, @RequestBody Cliente atualizado) {
        try {
            Cliente clienteEditado = clienteService.editarCliente(cpf, atualizado);
            return ResponseEntity.ok(clienteEditado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
