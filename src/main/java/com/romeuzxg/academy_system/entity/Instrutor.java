package com.romeuzxg.academy_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_instrutor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instrutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String especialidade;

    @OneToMany(mappedBy = "instrutor")
    private List<Turma> turmas = new ArrayList<>();

}
