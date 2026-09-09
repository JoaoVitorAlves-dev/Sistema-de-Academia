package com.romeuzxg.academy_system.repository;

import com.romeuzxg.academy_system.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

    Optional<Roles> findByNome(String role);
}
