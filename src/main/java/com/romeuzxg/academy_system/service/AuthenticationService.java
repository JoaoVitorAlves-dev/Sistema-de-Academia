package com.romeuzxg.academy_system.service;

import com.romeuzxg.academy_system.config.TokenProvider;
import com.romeuzxg.academy_system.dto.request.LoginRequest;
import com.romeuzxg.academy_system.dto.request.RegisterRequest;
import com.romeuzxg.academy_system.dto.response.TokenResponse;
import com.romeuzxg.academy_system.entity.Aluno;
import com.romeuzxg.academy_system.entity.Roles;
import com.romeuzxg.academy_system.enums.RoleTypeEnum;
import com.romeuzxg.academy_system.repository.AlunoRepository;
import com.romeuzxg.academy_system.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AlunoRepository alunoRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    @Value("${JWT_EXPIRATION:3600000}")
    private long expirationTime;

    public void register(RegisterRequest registerRequest) throws BadRequestException {
        Aluno aluno = alunoRepository.findByEmail(registerRequest.getEmail()).orElse(null);

        if (aluno != null) {
            throw new BadRequestException("Aluno já cadastrado com este email");
        }

        Roles role = rolesRepository.findByNome(RoleTypeEnum.ALUNO.name())
                .orElseGet(() -> rolesRepository.save(Roles.builder()
                        .nome(RoleTypeEnum.ALUNO.name())
                        .build()));


        alunoRepository.save(Aluno.builder()
                .nome(registerRequest.getNome())
                .email(registerRequest.getEmail())
                .roles(Set.of(role))
                .senha(passwordEncoder.encode(registerRequest.getSenha()))
                .build());

    }

    public TokenResponse login(LoginRequest loginRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha()));
            String token = tokenProvider.gerarToken(authentication);
            return new TokenResponse(token, expirationTime);
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Credenciais invalidas");
        } catch (Exception e) {
            throw new Exception();
        }
    }


}
