// src/main/java/com/aps2ArqObj/APS2/DTO/ContaCreateDto.java
package com.aps2ArqObj.APS2.DTO;

import jakarta.validation.constraints.*;

/**
 * DTO para criação de ContaCorrente (usado dentro de ClienteCreateDto ou standalone).
 */
public record ContaCreateDto(
        @NotBlank
        String agencia,

        @NotBlank
        String numero,

        @PositiveOrZero
        float saldoInicial,

        @PositiveOrZero
        float limite
) {}
