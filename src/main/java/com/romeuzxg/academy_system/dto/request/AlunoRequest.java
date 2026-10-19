package com.romeuzxg.academy_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AlunoRequest(
        @NotBlank String nome,
        @NotBlank String email,
        @NotBlank String senha,
        @NotNull LocalDate dataNascimento) {
}
