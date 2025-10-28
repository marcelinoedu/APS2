// src/main/java/com/aps2ArqObj/APS2/Controllers/ClienteController.java
package com.aps2ArqObj.APS2.Controllers;

import com.aps2ArqObj.APS2.DTO.*;
import com.aps2ArqObj.APS2.Models.Cliente;
import com.aps2ArqObj.APS2.Security.Autenticado;
import com.aps2ArqObj.APS2.Services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Autenticado
    @PostMapping
    public ResponseEntity<ClienteResponseDto> cadastrar(@Valid @RequestBody ClienteCreateDto dto) {
        Cliente saved = clienteService.cadastrarCliente(DtoMapper.toClienteEntity(dto));
        return ResponseEntity.ok(DtoMapper.toClienteResponseDto(saved));
    }

    @GetMapping
    public ResponseEntity<List<ClienteSummaryDto>> listar() {
        List<ClienteSummaryDto> list = clienteService.listarClientes()
                .stream()
                .map(DtoMapper::toClienteSummaryDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDto> buscar(@PathVariable String cpf) {
        return clienteService.buscarPorCpf(cpf)
                .map(DtoMapper::toClienteResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Autenticado
    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDto> editar(@PathVariable String cpf, @Valid @RequestBody ClienteUpdateDto atualizado) {
        return clienteService.buscarPorCpf(cpf)
                .map(existing -> {
                    DtoMapper.updateClienteFromDto(existing, atualizado);
                    Cliente edited = clienteService.editarCliente(cpf, existing);
                    return ResponseEntity.ok(DtoMapper.toClienteResponseDto(edited));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
