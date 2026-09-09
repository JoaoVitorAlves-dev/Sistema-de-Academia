package com.romeuzxg.academy_system.dto.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AlunoRequest(String nome, String email, String senha, LocalDate dataNascimento) {
}
