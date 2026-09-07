package com.romeuzxg.academy_system.dto.request;

import java.time.LocalDate;

public record AlunoRequest(String nome, String email, LocalDate dataNascimento) {
}
