package com.aps2ArqObj.APS2.Controllers;

import com.aps2ArqObj.APS2.Models.Cartao;
import com.aps2ArqObj.APS2.Models.ContaCorrente;
import com.aps2ArqObj.APS2.Security.Autenticado;
import com.aps2ArqObj.APS2.Services.CartaoService;
import com.aps2ArqObj.APS2.Services.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<Cartao> emitirCartao(
            @PathVariable String numeroConta,
            @RequestParam String tipo) {

        Optional<ContaCorrente> contaOpt = contaService.buscarPorNumero(numeroConta);
        if (contaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = new Cartao(
                UUID.randomUUID().toString(),
                tipo,
                LocalDate.now().plusYears(4)
        );

        return ResponseEntity.ok(cartaoService.emitirCartao(contaOpt.get(), cartao));
    }


    @GetMapping
    public ResponseEntity<List<Cartao>> listarCartoes() {
        return ResponseEntity.ok(cartaoService.listarCartoes());
    }


    @GetMapping("/conta/{numeroConta}")
    public ResponseEntity<List<Cartao>> listarCartoesConta(@PathVariable String numeroConta) {
        Optional<ContaCorrente> contaOpt = contaService.buscarPorNumero(numeroConta);
        if (contaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartaoService.listarCartoesConta(contaOpt.get()));
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
    public ResponseEntity<String> statusCartao(@PathVariable String numero) {
        for (Cartao cartao : cartaoService.listarCartoes()) {
            if (cartao.getNumeroCartao().equals(numero)) {
                boolean ativo = cartaoService.cartaoAtivo(cartao);
                return ResponseEntity.ok(ativo ? "ATIVO" : "INATIVO");
            }
        }
        return ResponseEntity.notFound().build();
    }
}
