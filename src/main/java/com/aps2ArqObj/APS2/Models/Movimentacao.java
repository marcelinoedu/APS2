package com.aps2ArqObj.APS2.Models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "movimentacoes")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float valor;

    private String tipo;

    private LocalDate data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_numero")
    private ContaCorrente conta;

    public Movimentacao() {}

    public Movimentacao(float valor, String tipo, LocalDate data, ContaCorrente conta) {
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
        this.conta = conta;
    }

    public Long getId() { return id; }

    public float getValor() { return valor; }

    public String getTipo() { return tipo; }

    public LocalDate getData() { return data; }

    public ContaCorrente getConta() { return conta; }
}
