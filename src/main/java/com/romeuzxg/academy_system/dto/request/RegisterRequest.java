package com.romeuzxg.academy_system.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank String nome,
        @NotBlank String email,
        @NotBlank String senha

) {
}
