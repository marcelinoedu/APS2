// src/main/java/com/aps2ArqObj/APS2/DTO/CartaoResponseDto.java
package com.aps2ArqObj.APS2.DTO;

import java.time.LocalDate;

/**
 * DTO de resposta para Cartao.
 */
public record CartaoResponseDto(
        String numeroCartao,
        String tipo,
        LocalDate validade,
        String status
) {}
