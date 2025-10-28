// src/main/java/com/aps2ArqObj/APS2/DTO/ClienteResponseDto.java
package com.aps2ArqObj.APS2.DTO;

import java.time.LocalDate;

/**
 * DTO de resposta com informação do cliente (sem senha).
 */
public record ClienteResponseDto(
        String cpf,
        String nome,
        LocalDate dataNascimento,
        float salario,
        String email,
        ContaResponseDto conta
) {}
