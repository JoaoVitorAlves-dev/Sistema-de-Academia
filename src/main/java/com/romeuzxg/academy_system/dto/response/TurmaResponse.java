package com.romeuzxg.academy_system.dto.response;

import java.util.List;

public record TurmaResponse(Long id, String nome, Integer horario, Integer capacidade, List<Long> instrutorId) {
}
