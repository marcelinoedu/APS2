package com.aps2ArqObj.APS2.Models;

import java.time.LocalDate;

public class Cliente {
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private float salario;
    private ContaCorrente conta;
    private String email;
    private String senha;

    public Cliente(String cpf, String nome, LocalDate dataNascimento, float salario) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public float getSalario() { return salario; }
    public void setSalario(float salario) { this.salario = salario; }

    public ContaCorrente getConta() { return conta; }
    public void setConta(ContaCorrente conta) { this.conta = conta; }
}
