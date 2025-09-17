package com.aps2ArqObj.APS2.Models;


import java.util.*;
import java.time.LocalDate;

public class ContaCorrente {
    private String agencia;
    private String numero;
    private float saldo;
    private float limite;

    private final List<Movimentacao> movimentacoes = new ArrayList<>();
    private final List<Cartao> cartoes = new ArrayList<>();

    public ContaCorrente(String agencia, String numero, float saldoInicial, float limite) {
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldoInicial;
        this.limite = limite;
    }


    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public float getSaldo() { return saldo; }
    public float getLimite() { return limite; }

    public List<Movimentacao> listaMovimentacoes() { return Collections.unmodifiableList(movimentacoes); }
    public List<Cartao> listaCartoes() { return Collections.unmodifiableList(cartoes); }

    public boolean saque(float valor) {
        if (valor <= 0) return false;

        if (saldo - valor < -limite) return false;
        saldo -= valor;
        movimentacoes.add(new Movimentacao(valor, "SAQUE", LocalDate.now()));
        return true;
    }

    public boolean deposito(float valor) {
        if (valor <= 0) return false;
        saldo += valor;
        movimentacoes.add(new Movimentacao(valor, "DEPOSITO", LocalDate.now()));
        return true;
    }

    public void adicionarCartao(Cartao c) { cartoes.add(c); }

    public Optional<Cartao> buscarCartaoPorNumero(String numeroCartao) {
        return cartoes.stream().filter(c -> c.getNumeroCartao().equals(numeroCartao)).findFirst();
    }
}
