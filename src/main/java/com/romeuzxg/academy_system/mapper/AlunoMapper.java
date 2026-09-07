package com.romeuzxg.academy_system.mapper;

import com.romeuzxg.academy_system.dto.request.AlunoRequest;
import com.romeuzxg.academy_system.dto.response.AlunoResponse;
import com.romeuzxg.academy_system.entity.Aluno;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AlunoMapper {

    public Aluno toEntity(AlunoRequest alunoRequest) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoRequest.nome());
        aluno.setEmail(alunoRequest.email());
        aluno.setDataNascimento(alunoRequest.dataNascimento());
        return aluno;
    }

    public AlunoResponse toDTO(Aluno aluno) {
        return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getDataNascimento());
    }

}
