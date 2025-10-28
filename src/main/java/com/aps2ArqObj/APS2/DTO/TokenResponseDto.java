package com.aps2ArqObj.APS2.DTO;

import java.time.Instant;

public record TokenResponseDto(
        String token,
        Instant expiresAt
) {}
