// src/main/java/com/aps2ArqObj/APS2/DTO/MovimentacaoDto.java
package com.aps2ArqObj.APS2.DTO;

import java.time.LocalDate;

/**
 * DTO para movimentações (saque/deposito).
 */
public record MovimentacaoDto(
        Long id,
        float valor,
        String tipo,
        LocalDate data,
        String contaNumero
) {}
