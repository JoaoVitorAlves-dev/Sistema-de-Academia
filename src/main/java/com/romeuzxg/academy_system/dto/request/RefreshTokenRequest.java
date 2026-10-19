package com.romeuzxg.academy_system.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank String accessToken
) {
}
