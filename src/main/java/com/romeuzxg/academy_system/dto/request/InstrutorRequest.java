package com.romeuzxg.academy_system.dto.request;

import jakarta.validation.constraints.NotBlank;

public record InstrutorRequest(
        @NotBlank String nome,
        @NotBlank String especialidade) {
}
