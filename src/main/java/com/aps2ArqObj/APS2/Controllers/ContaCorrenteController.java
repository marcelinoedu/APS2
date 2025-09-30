package com.aps2ArqObj.APS2.Controllers;

import com.aps2ArqObj.APS2.Models.ContaCorrente;
import com.aps2ArqObj.APS2.Models.Movimentacao;
import com.aps2ArqObj.APS2.Security.Autenticado;
import com.aps2ArqObj.APS2.Services.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class ContaCorrenteController {

    private final ContaService contaService;

    public ContaCorrenteController(ContaService contaService) {
        this.contaService = contaService;
    }


    @Autenticado
    @PostMapping
    public ResponseEntity<ContaCorrente> cadastrar(@RequestBody ContaCorrente conta) {
        return ResponseEntity.ok(contaService.cadastrarConta(conta));
    }


    @GetMapping
    public ResponseEntity<List<ContaCorrente>> listar() {
        return ResponseEntity.ok(contaService.listarContas());
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
    public ResponseEntity<List<Movimentacao>> movimentacoes(@PathVariable String numero) {
        Optional<ContaCorrente> contaOpt = contaService.buscarPorNumero(numero);
        if (contaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contaOpt.get().listaMovimentacoes());
    }
}
