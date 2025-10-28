// src/main/java/com/aps2ArqObj/APS2/DTO/ContaResponseDto.java
package com.aps2ArqObj.APS2.DTO;

import java.util.List;

/**
 * DTO de resposta para ContaCorrente, incluindo cartões e movimentações.
 */
public record ContaResponseDto(
        String agencia,
        String numero,
        float saldo,
        float limite,
        List<CartaoResponseDto> cartoes,
        List<MovimentacaoDto> movimentacoes
) {}
