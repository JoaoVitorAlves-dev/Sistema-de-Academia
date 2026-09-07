package com.romeuzxg.academy_system.mapper;

import com.romeuzxg.academy_system.dto.request.MatriculaRequest;
import com.romeuzxg.academy_system.dto.response.MatriculaResponse;
import com.romeuzxg.academy_system.entity.Matricula;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MatriculaMapper {

    public Matricula toEntity(MatriculaRequest matriculaRequest) {
        Matricula matricula = new Matricula();
        matricula.setDataMatricula(matriculaRequest.dataMatricula());
        matricula.setStatus(matriculaRequest.status());
        return matricula;
    }

    public MatriculaResponse toDTO(Matricula matricula) {
        return new MatriculaResponse(
                matricula.getId(), matricula.getAluno().getId(), matricula.getTurma().getId(), matricula.getDataMatricula(), matricula.getStatus());
    }

}
