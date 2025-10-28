// src/main/java/com/aps2ArqObj/APS2/Controllers/CartaoController.java
package com.aps2ArqObj.APS2.Controllers;

import com.aps2ArqObj.APS2.DTO.*;
import com.aps2ArqObj.APS2.Models.ContaCorrente;
import com.aps2ArqObj.APS2.Models.Cartao;
import com.aps2ArqObj.APS2.Security.Autenticado;
import com.aps2ArqObj.APS2.Services.CartaoService;
import com.aps2ArqObj.APS2.Services.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    private final CartaoService cartaoService;
    private final ContaService contaService;

    public CartaoController(CartaoService cartaoService, ContaService contaService) {
        this.cartaoService = cartaoService;
        this.contaService = contaService;
    }

    @Autenticado
    @PostMapping("/{numeroConta}")
    public ResponseEntity<CartaoResponseDto> emitirCartao(
            @PathVariable String numeroConta,
            @RequestParam String tipo,
            @RequestParam(required = false) String validade // optional yyyy-MM-dd
    ) {
        Optional<ContaCorrente> contaOpt = contaService.buscarPorNumero(numeroConta);
        if (contaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        LocalDate val;
        if (validade != null && !validade.isBlank()) {
            val = LocalDate.parse(validade);
        } else {
            val = LocalDate.now().plusYears(4);
        }

        Cartao cartao = new Cartao(
                UUID.randomUUID().toString(),
                tipo,
                val
        );

        Cartao emitted = cartaoService.emitirCartao(contaOpt.get(), cartao);
        return ResponseEntity.ok(DtoMapper.toCartaoResponseDto(emitted));
    }

    @GetMapping
    public ResponseEntity<List<CartaoResponseDto>> listarCartoes() {
        List<CartaoResponseDto> dtos = cartaoService.listarCartoes()
                .stream()
                .map(DtoMapper::toCartaoResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/conta/{numeroConta}")
    public ResponseEntity<List<CartaoResponseDto>> listarCartoesConta(@PathVariable String numeroConta) {
        Optional<ContaCorrente> contaOpt = contaService.buscarPorNumero(numeroConta);
        if (contaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<CartaoResponseDto> dtos = cartaoService.listarCartoesConta(contaOpt.get())
                .stream()
                .map(DtoMapper::toCartaoResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Autenticado
    @DeleteMapping("/{numero}")
    public ResponseEntity<String> cancelarCartao(@PathVariable String numero) {
        for (Cartao cartao : cartaoService.listarCartoes()) {
            if (cartao.getNumeroCartao().equals(numero)) {
                boolean cancelado = cartaoService.cancelarCartao(cartao);
                return cancelado
                        ? ResponseEntity.ok("Cartão cancelado com sucesso")
                        : ResponseEntity.badRequest().body("Cartão já estava cancelado");
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{numero}/status")
    public ResponseEntity<CartaoStatusDto> statusCartao(@PathVariable String numero) {
        for (Cartao cartao : cartaoService.listarCartoes()) {
            if (cartao.getNumeroCartao().equals(numero)) {
                boolean ativo = cartaoService.cartaoAtivo(cartao);
                return ResponseEntity.ok(new CartaoStatusDto(cartao.getNumeroCartao(), ativo ? "ATIVO" : "INATIVO"));
            }
        }
        return ResponseEntity.notFound().build();
    }
}
