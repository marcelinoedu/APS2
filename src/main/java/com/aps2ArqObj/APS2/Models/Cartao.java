package com.aps2ArqObj.APS2.Models;

import java.time.LocalDate;

public class Cartao {
    private String numeroCartao;
    private String tipo;
    private LocalDate validade;
    private String status;

    public Cartao(String numeroCartao, String tipo, LocalDate validade) {
        this.numeroCartao = numeroCartao;
        this.tipo = tipo;
        this.validade = validade;
        this.status = "ATIVO";
    }

    public String getNumeroCartao() { return numeroCartao; }
    public void setNumeroCartao(String numeroCartao) { this.numeroCartao = numeroCartao; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDate getValidade() { return validade; }
    public void setValidade(LocalDate validade) { this.validade = validade; }

    public String getStatus() { return status; }
    private void setStatus(String status) { this.status = status; }

    public boolean isExpired() {
        return validade.isBefore(LocalDate.now());
    }

    public boolean cancelaCartao() {
        if (!"CANCELADO".equals(status)) {
            setStatus("CANCELADO");
            return true;
        }
        return false;
    }
}
