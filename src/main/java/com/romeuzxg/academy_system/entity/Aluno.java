package com.romeuzxg.academy_system.entity;

import com.romeuzxg.academy_system.entity.roles.RoleAluno;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_aluno")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 60, nullable = false)
    private String nome;

    @Column(name = "email", length = 40, unique = true, nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "aluno")
    private List<Matricula> matriculas = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "roles", nullable = false)
    private RoleAluno roles;

}
