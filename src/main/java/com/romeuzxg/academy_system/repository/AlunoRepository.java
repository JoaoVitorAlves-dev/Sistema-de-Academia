package com.romeuzxg.academy_system.repository;

import com.romeuzxg.academy_system.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByEmail(String email);

    Optional<Aluno> findByEmail(String email);

}
