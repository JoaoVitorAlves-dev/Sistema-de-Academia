package com.romeuzxg.academy_system.service;

import com.romeuzxg.academy_system.dto.request.AlunoRequest;
import com.romeuzxg.academy_system.entity.Aluno;
import com.romeuzxg.academy_system.entity.Roles;
import com.romeuzxg.academy_system.enums.RoleTypeEnum;
import com.romeuzxg.academy_system.repository.AlunoRepository;
import com.romeuzxg.academy_system.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AlunoRepository alunoRepository;
    private final RolesRepository rolesRepository;

    public void register( AlunoRequest alunoRequest) throws BadRequestException{
        Aluno aluno = alunoRepository.findByEmail(alunoRequest.email()).orElse(null);

        if (aluno != null) {
            throw new BadRequestException("Aluno já cadastrado com este email");
        }

        Roles role = rolesRepository.findByNome(RoleTypeEnum.ALUNO.name())
                .orElseGet(() -> rolesRepository.save(Roles.builder()
                                .nome(RoleTypeEnum.ALUNO.name())
                        .build()));


        alunoRepository.save(aluno);
    }

}
