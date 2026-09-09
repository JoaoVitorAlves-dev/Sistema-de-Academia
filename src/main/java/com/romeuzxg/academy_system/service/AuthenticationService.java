package com.romeuzxg.academy_system.service;

import com.romeuzxg.academy_system.dto.request.AlunoRequest;
import com.romeuzxg.academy_system.entity.Aluno;
import com.romeuzxg.academy_system.entity.Roles;
import com.romeuzxg.academy_system.enums.RoleTypeEnum;
import com.romeuzxg.academy_system.repository.AlunoRepository;
import com.romeuzxg.academy_system.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AlunoRepository alunoRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(AlunoRequest alunoRequest) throws BadRequestException {
        Aluno aluno = alunoRepository.findByEmail(alunoRequest.email()).orElse(null);

        if (aluno != null) {
            throw new BadRequestException("Aluno já cadastrado com este email");
        }

        Roles role = rolesRepository.findByNome(RoleTypeEnum.ALUNO.name())
                .orElseGet(() -> rolesRepository.save(Roles.builder()
                        .nome(RoleTypeEnum.ALUNO.name())
                        .build()));


        alunoRepository.save(Aluno.builder()
                .nome(alunoRequest.nome())
                .email(alunoRequest.email())
                .roles(Set.of(role))
                .senha(passwordEncoder.encode(alunoRequest.senha()))
                .build());
    }

}
