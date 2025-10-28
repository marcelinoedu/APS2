
package com.aps2ArqObj.APS2.DTO;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank
        String cpf,
        @NotBlank
        String senha
) {

}
