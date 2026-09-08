package com.romeuzxg.academy_system.service;

import com.romeuzxg.academy_system.dto.request.AlunoRequest;
import com.romeuzxg.academy_system.dto.response.AlunoResponse;
import com.romeuzxg.academy_system.entity.Aluno;
import com.romeuzxg.academy_system.exceptions.IdNotFoundException;
import com.romeuzxg.academy_system.mapper.AlunoMapper;
import com.romeuzxg.academy_system.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public List<AlunoResponse> findAll() {
        return alunoRepository.findAll().stream()
                .map(AlunoMapper::toDTO)
                .toList();
    }

    public AlunoResponse findById(Long id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID Não existe"));
        return AlunoMapper.toDTO(aluno);
    }

    public AlunoResponse save(AlunoRequest alunoRequest) {
        Aluno save = alunoRepository.save(AlunoMapper.toEntity(alunoRequest));
        return AlunoMapper.toDTO(save);
    }

    public AlunoResponse updateById(Long id, AlunoRequest alunoRequest) {
        alunoRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID Não existe"));
        Aluno entity = AlunoMapper.toEntity(alunoRequest);
        entity.setId(id);
        Aluno save = alunoRepository.save((entity));
        return AlunoMapper.toDTO(save);
    }

    public void deleteById(Long id) {
        alunoRepository.deleteById(id);
    }

}
