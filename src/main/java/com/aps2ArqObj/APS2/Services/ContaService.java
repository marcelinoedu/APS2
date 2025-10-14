package com.aps2ArqObj.APS2.Services;

import com.aps2ArqObj.APS2.Models.ContaCorrente;
import com.aps2ArqObj.APS2.Repositories.ContaCorrenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    private final ContaCorrenteRepository contaRepository;

    public ContaService(ContaCorrenteRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Transactional
    public ContaCorrente cadastrarConta(ContaCorrente conta) {
        return contaRepository.save(conta);
    }

    public List<ContaCorrente> listarContas() {
        return contaRepository.findAll();
    }

    public Optional<ContaCorrente> buscarPorNumero(String numero) {
        return contaRepository.findById(numero);
    }

    @Transactional
    public boolean saque(String numero, float valor) {
        ContaCorrente conta = contaRepository.findById(numero).orElse(null);
        if (conta != null) {
            boolean ok = conta.saque(valor);
            if (ok) contaRepository.save(conta); // persist changes & novas movimentações (cascade)
            return ok;
        }
        return false;
    }

    @Transactional
    public boolean deposito(String numero, float valor) {
        ContaCorrente conta = contaRepository.findById(numero).orElse(null);
        if (conta != null) {
            boolean ok = conta.deposito(valor);
            if (ok) contaRepository.save(conta);
            return ok;
        }
        return false;
    }
}
