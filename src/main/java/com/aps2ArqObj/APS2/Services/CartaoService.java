package com.aps2ArqObj.APS2.Services;

import com.aps2ArqObj.APS2.Models.Cartao;
import com.aps2ArqObj.APS2.Models.ContaCorrente;
import com.aps2ArqObj.APS2.Repositories.CartaoRepository;
import com.aps2ArqObj.APS2.Repositories.ContaCorrenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CartaoService {

    private final CartaoRepository cartaoRepository;
    private final ContaCorrenteRepository contaRepository;

    public CartaoService(CartaoRepository cartaoRepository, ContaCorrenteRepository contaRepository) {
        this.cartaoRepository = cartaoRepository;
        this.contaRepository = contaRepository;
    }

    @Transactional
    public Cartao emitirCartao(ContaCorrente conta, Cartao cartao) {
        conta.adicionarCartao(cartao);
        cartaoRepository.save(cartao);
        contaRepository.save(conta);
        return cartao;
    }

    public List<Cartao> listarCartoes() {
        return cartaoRepository.findAll();
    }

    public List<Cartao> listarCartoesConta(ContaCorrente conta) {
        return cartaoRepository.findByContaNumero(conta.getNumero());
    }

    @Transactional
    public boolean cancelarCartao(Cartao cartao) {
        boolean changed = cartao.cancelaCartao();
        if (changed) cartaoRepository.save(cartao);
        return changed;
    }

    public boolean cartaoAtivo(Cartao cartao) {
        return !"CANCELADO".equals(cartao.getStatus()) && !cartao.isExpired();
    }
}
