package com.romeuzxg.academy_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TurmaRequest(
        @NotBlank String nome,
        @NotBlank String horario,
        @NotNull Integer capacidade,
        @NotNull Long instrutorId) {
}
