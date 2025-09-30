package com.aps2ArqObj.APS2.Services;

import com.aps2ArqObj.APS2.Models.Cartao;
import com.aps2ArqObj.APS2.Models.ContaCorrente;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class CartaoService {
    private final List<Cartao> cartoes = new ArrayList<>();

    public Cartao emitirCartao(ContaCorrente conta, Cartao cartao) {
        conta.adicionarCartao(cartao);
        cartoes.add(cartao);
        return cartao;
    }

    public List<Cartao> listarCartoes() {
        return new ArrayList<>(cartoes);
    }

    public List<Cartao> listarCartoesConta(ContaCorrente conta) {
        return conta.listaCartoes();
    }

    public boolean cancelarCartao(Cartao cartao) {
        return cartao.cancelaCartao();
    }

    public boolean cartaoAtivo(Cartao cartao) {
        return !"CANCELADO".equals(cartao.getStatus()) && !cartao.isExpired();
    }
}
