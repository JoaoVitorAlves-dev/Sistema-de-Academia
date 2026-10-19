package com.romeuzxg.academy_system.repository;

import com.romeuzxg.academy_system.entity.Aluno;
import com.romeuzxg.academy_system.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByAluno(Aluno aluno);

}
