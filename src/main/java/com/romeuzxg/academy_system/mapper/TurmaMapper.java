package com.romeuzxg.academy_system.mapper;

import com.romeuzxg.academy_system.dto.request.TurmaRequest;
import com.romeuzxg.academy_system.dto.response.TurmaResponse;
import com.romeuzxg.academy_system.entity.Turma;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TurmaMapper {

    public Turma toEntity(TurmaRequest turmaRequest) {
        Turma turma = new Turma();
        turma.setNome(turmaRequest.nome());
        turma.setCapacidade(turmaRequest.capacidade());
        turma.setHorario(turmaRequest.horario());
        return turma;
    }

    public TurmaResponse toDTO(Turma turma) {
        return new TurmaResponse(turma.getId(), turma.getNome(), turma.getHorario(), turma.getCapacidade(), turma.getInstrutor().getId());
    }

}
