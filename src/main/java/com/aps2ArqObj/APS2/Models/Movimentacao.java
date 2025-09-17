package com.aps2ArqObj.APS2.Models;


import java.time.LocalDate;

public class Movimentacao {
    private final float valor;
    private final String tipo;
    private final LocalDate data;

    public Movimentacao(float valor, String tipo, LocalDate data) {
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
    }

    public float getValor() { return valor; }
    public String getTipo() { return tipo; }
    public LocalDate getData() { return data; }
}

