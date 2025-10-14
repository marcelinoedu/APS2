package com.aps2ArqObj.APS2.Models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cartoes")
public class Cartao {

    @Id
    @Column(name = "numero_cartao", length = 100)
    private String numeroCartao;

    private String tipo;

    private LocalDate validade;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_numero")
    private ContaCorrente conta;

    public Cartao() {}

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

    public ContaCorrente getConta() { return conta; }
    public void setConta(ContaCorrente conta) { this.conta = conta; }

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
