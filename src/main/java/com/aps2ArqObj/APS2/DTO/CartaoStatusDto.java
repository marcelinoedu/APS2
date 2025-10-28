// src/main/java/com/aps2ArqObj/APS2/DTO/CartaoStatusDto.java
package com.aps2ArqObj.APS2.DTO;

/**
 * DTO simples para retorno de status de cartão.
 */
public record CartaoStatusDto(
        String numeroCartao,
        String status
) {}
