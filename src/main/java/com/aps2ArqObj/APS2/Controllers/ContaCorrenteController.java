// src/main/java/com/aps2ArqObj/APS2/Controllers/ContaCorrenteController.java
package com.aps2ArqObj.APS2.Controllers;

import com.aps2ArqObj.APS2.DTO.*;
import com.aps2ArqObj.APS2.Models.ContaCorrente;
import com.aps2ArqObj.APS2.Models.Movimentacao;
import com.aps2ArqObj.APS2.Security.Autenticado;
import com.aps2ArqObj.APS2.Services.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contas")
public class ContaCorrenteController {

    private final ContaService contaService;

    public ContaCorrenteController(ContaService contaService) {
        this.contaService = contaService;
    }

    @Autenticado
    @PostMapping
    public ResponseEntity<ContaResponseDto> cadastrar(@Valid @RequestBody ContaCreateDto dto) {
        ContaCorrente saved = contaService.cadastrarConta(DtoMapper.toContaEntity(dto));
        return ResponseEntity.ok(DtoMapper.toContaResponseDto(saved));
    }

    @GetMapping
    public ResponseEntity<List<ContaResponseDto>> listar() {
        List<ContaResponseDto> list = contaService.listarContas().stream()
                .map(DtoMapper::toContaResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @Autenticado
    @PostMapping("/{numero}/saque")
    public ResponseEntity<String> saque(@PathVariable String numero, @RequestParam float valor) {
        boolean sucesso = contaService.saque(numero, valor);
        return sucesso
                ? ResponseEntity.ok("Saque realizado com sucesso")
                : ResponseEntity.badRequest().body("Falha no saque");
    }

    @Autenticado
    @PostMapping("/{numero}/deposito")
    public ResponseEntity<String> deposito(@PathVariable String numero, @RequestParam float valor) {
        boolean sucesso = contaService.deposito(numero, valor);
        return sucesso
                ? ResponseEntity.ok("Depósito realizado com sucesso")
                : ResponseEntity.badRequest().body("Falha no depósito");
    }

    @GetMapping("/{numero}/movimentacoes")
    public ResponseEntity<List<MovimentacaoDto>> movimentacoes(@PathVariable String numero) {
        Optional<ContaCorrente> contaOpt = contaService.buscarPorNumero(numero);
        if (contaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<MovimentacaoDto> dtos = contaOpt.get().listaMovimentacoes().stream()
                .map(DtoMapper::toMovimentacaoDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
