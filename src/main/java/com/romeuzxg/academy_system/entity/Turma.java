package com.romeuzxg.academy_system.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "horario")
    private Integer horario;

    @Column(name = "capacidade")
    private Integer capacidade;

    @OneToMany(mappedBy = "turma")
    private List<Matricula> matriculas = new ArrayList<>();

    //relacionamento instrutor
}
