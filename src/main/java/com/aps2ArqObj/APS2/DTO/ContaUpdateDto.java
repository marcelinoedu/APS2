// src/main/java/com/aps2ArqObj/APS2/DTO/ContaUpdateDto.java
package com.aps2ArqObj.APS2.DTO;

import jakarta.validation.constraints.*;

/**
 * DTO para atualização de conta.
 */
public record ContaUpdateDto(
        @NotBlank
        String agencia,

        @NotBlank
        String numero,

        @PositiveOrZero
        float saldo,

        @PositiveOrZero
        float limite
) {}
