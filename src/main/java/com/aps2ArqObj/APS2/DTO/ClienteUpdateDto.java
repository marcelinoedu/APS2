// src/main/java/com/aps2ArqObj/APS2/DTO/ClienteUpdateDto.java
package com.aps2ArqObj.APS2.DTO;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * DTO para atualizar dados do cliente.
 * CPF é path variable (não incluso aqui).
 */
public record ClienteUpdateDto(
        @NotBlank
        String nome,

        @NotNull @Past
        LocalDate dataNascimento,

        @PositiveOrZero
        float salario,

        @NotBlank @Email
        String email,

        /**
         * Senha é opcional: se null, não altera.
         */
        String senha,

        ContaUpdateDto conta
) {}
