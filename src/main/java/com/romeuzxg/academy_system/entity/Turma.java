package com.romeuzxg.academy_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_turma")
@Getter
@Setter
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "horario")
    private String horario;

    @Column(name = "capacidade")
    private Integer capacidade;

    @OneToMany(mappedBy = "turma")
    private List<Matricula> matriculas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "instrutor_id")
    private Instrutor instrutor;
}
