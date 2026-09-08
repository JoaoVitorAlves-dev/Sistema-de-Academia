package com.romeuzxg.academy_system.service;

import com.romeuzxg.academy_system.dto.request.MatriculaRequest;
import com.romeuzxg.academy_system.dto.response.MatriculaResponse;
import com.romeuzxg.academy_system.entity.Aluno;
import com.romeuzxg.academy_system.entity.Matricula;
import com.romeuzxg.academy_system.entity.Turma;
import com.romeuzxg.academy_system.exceptions.IdNotFoundException;
import com.romeuzxg.academy_system.mapper.MatriculaMapper;
import com.romeuzxg.academy_system.repository.AlunoRepository;
import com.romeuzxg.academy_system.repository.MatriculaRepository;
import com.romeuzxg.academy_system.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;

    public List<MatriculaResponse> findAll() {
        return matriculaRepository.findAll().stream()
                .map(MatriculaMapper::toDTO)
                .toList();
    }

    public MatriculaResponse findById(Long id) {
        Matricula matricula = matriculaRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID Não existe"));
        return MatriculaMapper.toDTO(matricula);
    }

    public MatriculaResponse save(MatriculaRequest matriculaRequest) {
        Aluno aluno = alunoRepository.findById(matriculaRequest.alunoId()).orElseThrow(() -> new IdNotFoundException("ID Não existe"));
        Turma turma = turmaRepository.findById(matriculaRequest.turmaId()).orElseThrow(() -> new IdNotFoundException("ID Não existe"));
        Matricula save = matriculaRepository.save(MatriculaMapper.toEntity(aluno, turma, matriculaRequest));
        return MatriculaMapper.toDTO(save);
    }

    public MatriculaResponse updateById(Long id, MatriculaRequest matriculaRequest) {
        matriculaRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID Não existe"));
        Aluno aluno = alunoRepository.findById(matriculaRequest.alunoId()).orElseThrow(() -> new IdNotFoundException("ID Não existe"));
        Turma turma = turmaRepository.findById(matriculaRequest.turmaId()).orElseThrow(() -> new IdNotFoundException("ID Não existe"));
        Matricula entity = MatriculaMapper.toEntity(aluno, turma, matriculaRequest);
        entity.setId(id);
        Matricula save = matriculaRepository.save(entity);
        return MatriculaMapper.toDTO(save);
    }

    public void deleteById(Long id) {
        matriculaRepository.deleteById(id);
    }

}
