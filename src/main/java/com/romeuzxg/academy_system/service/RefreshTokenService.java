package com.romeuzxg.academy_system.service;

import com.romeuzxg.academy_system.entity.Aluno;
import com.romeuzxg.academy_system.entity.RefreshToken;
import com.romeuzxg.academy_system.exceptions.TokenExpiratedException;
import com.romeuzxg.academy_system.exceptions.TokenNotFoundException;
import com.romeuzxg.academy_system.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${app.jwt.refresh-expiration-days}")
    private long expirationInDays;

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public RefreshToken criar(Aluno aluno) {
        refreshTokenRepository.deleteByAluno(aluno);

        RefreshToken rt = new RefreshToken();
        rt.setAluno(aluno);
        rt.setExpiraEm(Instant.now().plus(expirationInDays, ChronoUnit.DAYS));
        rt.setToken(UUID.randomUUID().toString());
        return refreshTokenRepository.save(rt);
    }

    @Transactional
    public RefreshToken validarERotacionar(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(() -> new TokenNotFoundException("Token não encontrado."));

        if (refreshToken.getExpiraEm().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new TokenExpiratedException("Token expirado.");
        }

        return criar(refreshToken.getAluno());
    }

    @Transactional
    public void apagarPorToken(String token) {
        refreshTokenRepository.findByToken(token).ifPresent(refreshTokenRepository::delete);
    }

}
