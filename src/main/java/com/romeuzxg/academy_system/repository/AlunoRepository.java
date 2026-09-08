package com.romeuzxg.academy_system.repository;

import com.romeuzxg.academy_system.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Long id(Long id);
}
