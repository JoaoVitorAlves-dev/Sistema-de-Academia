package com.romeuzxg.academy_system.dto.request;

import java.time.LocalDate;

public record MatriculaRequest(Long alunoId, Long turmaId, LocalDate dataMatricula, Boolean status) {
}
