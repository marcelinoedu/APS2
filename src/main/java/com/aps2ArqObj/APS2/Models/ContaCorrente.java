package com.aps2ArqObj.APS2.Models;

import jakarta.persistence.*;
import java.util.*;
import java.time.LocalDate;

@Entity
@Table(name = "contas")
public class ContaCorrente {

    @Id
    @Column(length = 50)
    private String numero;

    private String agencia;

    private float saldo;

    private float limite;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Cartao> cartoes = new ArrayList<>();

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Movimentacao> movimentacoes = new ArrayList<>();

    public ContaCorrente() {}

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
    public void setSaldo(float saldo) { this.saldo = saldo; }

    public float getLimite() { return limite; }
    public void setLimite(float limite) { this.limite = limite; }

    public List<Movimentacao> listaMovimentacoes() { return Collections.unmodifiableList(movimentacoes); }
    public List<Cartao> listaCartoes() { return Collections.unmodifiableList(cartoes); }

    public boolean saque(float valor) {
        if (valor <= 0) return false;
        if (saldo - valor < -limite) return false;
        this.saldo -= valor;
        this.movimentacoes.add(new Movimentacao(valor, "SAQUE", LocalDate.now(), this));
        return true;
    }

    public boolean deposito(float valor) {
        if (valor <= 0) return false;
        this.saldo += valor;
        this.movimentacoes.add(new Movimentacao(valor, "DEPOSITO", LocalDate.now(), this));
        return true;
    }

    public void adicionarCartao(Cartao c) {
        c.setConta(this);
        this.cartoes.add(c);
    }

    public Optional<Cartao> buscarCartaoPorNumero(String numeroCartao) {
        return cartoes.stream().filter(c -> c.getNumeroCartao().equals(numeroCartao)).findFirst();
    }
}
