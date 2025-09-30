package com.aps2ArqObj.APS2.Services;

import com.aps2ArqObj.APS2.Models.ContaCorrente;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ContaService {
    private final Map<String, ContaCorrente> contas = new HashMap<>();

    public ContaCorrente cadastrarConta(ContaCorrente conta) {
        contas.put(conta.getNumero(), conta);
        return conta;
    }

    public List<ContaCorrente> listarContas() {
        return new ArrayList<>(contas.values());
    }

    public Optional<ContaCorrente> buscarPorNumero(String numero) {
        return Optional.ofNullable(contas.get(numero));
    }

    public boolean saque(String numero, float valor) {
        ContaCorrente conta = contas.get(numero);
        if (conta != null) return conta.saque(valor);
        return false;
    }

    public boolean deposito(String numero, float valor) {
        ContaCorrente conta = contas.get(numero);
        if (conta != null) return conta.deposito(valor);
        return false;
    }
}
