// src/main/java/com/aps2ArqObj/APS2/DTO/DtoMapper.java
package com.aps2ArqObj.APS2.DTO;

import com.aps2ArqObj.APS2.Models.*;
import java.util.List;
import java.util.stream.Collectors;

public final class DtoMapper {

    private DtoMapper() {}


    public static Cliente toClienteEntity(ClienteCreateDto dto) {
        Cliente c = new Cliente(
                dto.cpf(),
                dto.nome(),
                dto.dataNascimento(),
                dto.salario(),
                dto.email(),
                dto.senha()
        );
        if (dto.conta() != null) {
            ContaCorrente conta = toContaEntity(dto.conta());
            c.setConta(conta);
        }
        return c;
    }

    public static void updateClienteFromDto(Cliente cliente, ClienteUpdateDto dto) {
        cliente.setNome(dto.nome());
        cliente.setDataNascimento(dto.dataNascimento());
        cliente.setSalario(dto.salario());
        cliente.setEmail(dto.email());
        if (dto.senha() != null && !dto.senha().isBlank()) {
            cliente.setSenha(dto.senha());
        }
        if (dto.conta() != null) {
            ContaCorrente conta = cliente.getConta();
            if (conta == null) {
                conta = toContaEntity(new ContaCreateDto(dto.conta().agencia(), dto.conta().numero(), dto.conta().saldo(), dto.conta().limite()));
                cliente.setConta(conta);
            } else {
                conta.setAgencia(dto.conta().agencia());
                conta.setNumero(dto.conta().numero());
                conta.setSaldo(dto.conta().saldo());
                conta.setLimite(dto.conta().limite());
            }
        }
    }

    public static ClienteResponseDto toClienteResponseDto(Cliente c) {
        ContaResponseDto contaDto = null;
        if (c.getConta() != null) {
            contaDto = toContaResponseDto(c.getConta());
        }
        return new ClienteResponseDto(
                c.getCpf(),
                c.getNome(),
                c.getDataNascimento(),
                c.getSalario(),
                c.getEmail(),
                contaDto
        );
    }

    public static ClienteSummaryDto toClienteSummaryDto(Cliente c) {
        ContaWithCartoesDto cw = null;
        if (c.getConta() != null) {
            cw = toContaWithCartoesDto(c.getConta());
        }
        return new ClienteSummaryDto(c.getCpf(), c.getNome(), c.getDataNascimento(), c.getEmail(), cw);
    }


    public static ContaCorrente toContaEntity(ContaCreateDto dto) {
        return new ContaCorrente(dto.agencia(), dto.numero(), dto.saldoInicial(), dto.limite());
    }

    public static ContaResponseDto toContaResponseDto(ContaCorrente conta) {
        List<CartaoResponseDto> cartoes = conta.listaCartoes().stream()
                .map(DtoMapper::toCartaoResponseDto)
                .collect(Collectors.toList());

        List<MovimentacaoDto> movs = conta.listaMovimentacoes().stream()
                .map(DtoMapper::toMovimentacaoDto)
                .collect(Collectors.toList());

        return new ContaResponseDto(
                conta.getAgencia(),
                conta.getNumero(),
                conta.getSaldo(),
                conta.getLimite(),
                cartoes,
                movs
        );
    }

    public static ContaWithCartoesDto toContaWithCartoesDto(ContaCorrente conta) {
        List<CartaoResponseDto> cartoes = conta.listaCartoes().stream()
                .map(DtoMapper::toCartaoResponseDto)
                .collect(Collectors.toList());
        return new ContaWithCartoesDto(conta.getAgencia(), conta.getNumero(), conta.getSaldo(), conta.getLimite(), cartoes);
    }


    public static CartaoResponseDto toCartaoResponseDto(Cartao c) {
        return new CartaoResponseDto(c.getNumeroCartao(), c.getTipo(), c.getValidade(), c.getStatus());
    }


    public static MovimentacaoDto toMovimentacaoDto(Movimentacao m) {
        return new MovimentacaoDto(m.getId(), m.getValor(), m.getTipo(), m.getData(), m.getConta() != null ? m.getConta().getNumero() : null);
    }
}
