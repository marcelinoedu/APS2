// src/main/java/com/aps2ArqObj/APS2/DTO/ContaWithCartoesDto.java
package com.aps2ArqObj.APS2.DTO;

import java.util.List;


public record ContaWithCartoesDto(
        String agencia,
        String numero,
        float saldo,
        float limite,
        List<CartaoResponseDto> cartoes
) {}
