// src/main/java/com/aps2ArqObj/APS2/DTO/ClienteSummaryDto.java
package com.aps2ArqObj.APS2.DTO;

import java.time.LocalDate;

/**
 * DTO enxuto para listagem de clientes (sem dados sensíveis).
 */
public record ClienteSummaryDto(
        String cpf,
        String nome,
        LocalDate dataNascimento,
        String email,
        ContaWithCartoesDto conta
) {}
