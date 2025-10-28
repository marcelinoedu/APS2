// src/main/java/com/aps2ArqObj/APS2/DTO/CartaoCreateDto.java
package com.aps2ArqObj.APS2.DTO;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * DTO para emitir/solicitar cartão.
 * Número será gerado pelo sistema (não recebido no create).
 */
public record CartaoCreateDto(
        @NotBlank
        String tipo,         // ex: "DEBITO" ou "CREDITO"

        @NotNull
        LocalDate validade
) {}
