package com.romeuzxg.academy_system.service;

import com.romeuzxg.academy_system.config.TokenProvider;
import com.romeuzxg.academy_system.dto.request.AlunoRequest;
import com.romeuzxg.academy_system.dto.request.LoginRequest;
import com.romeuzxg.academy_system.dto.request.RefreshTokenRequest;
import com.romeuzxg.academy_system.dto.request.RegisterRequest;
import com.romeuzxg.academy_system.dto.response.RefreshTokenResponse;
import com.romeuzxg.academy_system.dto.response.TokenResponse;
import com.romeuzxg.academy_system.entity.Aluno;
import com.romeuzxg.academy_system.entity.RefreshToken;
import com.romeuzxg.academy_system.entity.roles.RoleAluno;
import com.romeuzxg.academy_system.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AlunoRepository alunoRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public RefreshTokenResponse refresh(RefreshTokenRequest req, Authentication auth) {
        RefreshToken refreshToken = refreshTokenService.validarERotacionar(req.accessToken());

        String s = tokenProvider.gerarToken(auth);

        return new RefreshTokenResponse(s, refreshToken.getToken());
    }

    public void register(RegisterRequest registerRequest) throws BadRequestException {
        Aluno aluno = alunoRepository.findByEmail(registerRequest.email()).orElse(null);

        if (aluno != null) {
            throw new BadRequestException("Aluno já cadastrado com este email");
        }

        alunoRepository.save(Aluno.builder()
                .nome(registerRequest.nome())
                .email(registerRequest.email())
                .roles(RoleAluno.ALUNO)
                .senha(passwordEncoder.encode(registerRequest.senha()))
                .build());
    }

    public TokenResponse login(LoginRequest loginRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha()));
            String token = tokenProvider.gerarToken(authentication);
            return new TokenResponse(token, expirationTime);
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Credenciais invalidas");
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void updateById(Long id, AlunoRequest req, Authentication auth) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!aluno.getEmail().equals(auth.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        aluno.setNome(req.nome());
        aluno.setEmail(req.email());
        aluno.setSenha(passwordEncoder.encode(req.senha()));
        aluno.setDataNascimento(req.dataNascimento());

        alunoRepository.save(aluno);
    }

    public void deleteById(Long id, Authentication auth) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!aluno.getEmail().equals(auth.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        aluno.setAtivo(false);
        alunoRepository.save(aluno);    }

}
