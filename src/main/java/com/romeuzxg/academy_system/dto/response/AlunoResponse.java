package com.romeuzxg.academy_system.dto.response;

import java.time.LocalDate;

public record AlunoResponse(Long id, String nome, String email, LocalDate dataNascimento) {
}
