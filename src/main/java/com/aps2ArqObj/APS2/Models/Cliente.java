package com.aps2ArqObj.APS2.Models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @Column(length = 14)
    private String cpf;

    private String nome;

    private LocalDate dataNascimento;

    private float salario;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "conta_numero", referencedColumnName = "numero")
    private ContaCorrente conta;

    private String email;

    private String senha;

    public Cliente() {}

    public Cliente(String cpf, String nome, LocalDate dataNascimento, float salario, String email, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.email = email;
        this.senha = senha;
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

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
