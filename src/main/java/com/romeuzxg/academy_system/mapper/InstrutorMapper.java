package com.romeuzxg.academy_system.mapper;

import com.romeuzxg.academy_system.dto.request.InstrutorRequest;
import com.romeuzxg.academy_system.dto.response.InstrutorResponse;
import com.romeuzxg.academy_system.entity.Instrutor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InstrutorMapper {

    public Instrutor toEntity(InstrutorRequest instrutorRequest) {
        Instrutor instrutor = new Instrutor();
        instrutor.setNome(instrutorRequest.nome());
        instrutor.setEspecialidade(instrutorRequest.especialidade());
        return instrutor;
    }

    public InstrutorResponse toDTO(Instrutor instrutor) {
        return new InstrutorResponse(instrutor.getId(), instrutor.getNome(), instrutor.getEspecialidade());
    }

}
