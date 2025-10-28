// src/main/java/com/aps2ArqObj/APS2/DTO/ClienteCreateDto.java
package com.aps2ArqObj.APS2.DTO;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * DTO para criação de Cliente (conta aninhada opcional).
 */
public record ClienteCreateDto(
        @NotBlank @Size(min = 11, max = 14)
        String cpf,

        @NotBlank
        String nome,

        @NotNull @Past
        LocalDate dataNascimento,

        @PositiveOrZero
        float salario,

        @NotBlank @Email
        String email,

        @NotBlank
        String senha,

        ContaCreateDto conta
) {}
