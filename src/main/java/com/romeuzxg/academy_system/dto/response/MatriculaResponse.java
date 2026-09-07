package com.romeuzxg.academy_system.dto.response;

import java.time.LocalDate;

public record MatriculaResponse(Long id, Long alunoId, Long turmaId, LocalDate dataMatricula, Boolean status) {
}
