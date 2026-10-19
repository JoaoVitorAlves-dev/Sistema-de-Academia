package com.romeuzxg.academy_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank String email,
        @NotBlank @Size(min = 6) String senha
) {
}
