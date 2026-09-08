package com.romeuzxg.academy_system.service;

import com.romeuzxg.academy_system.dto.request.TurmaRequest;
import com.romeuzxg.academy_system.dto.response.TurmaResponse;
import com.romeuzxg.academy_system.entity.Instrutor;
import com.romeuzxg.academy_system.entity.Turma;
import com.romeuzxg.academy_system.exceptions.IdNotFoundException;
import com.romeuzxg.academy_system.mapper.TurmaMapper;
import com.romeuzxg.academy_system.repository.InstrutorRepository;
import com.romeuzxg.academy_system.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final InstrutorRepository instrutorRepository;

    public List<TurmaResponse> findAll() {
        return turmaRepository.findAll().stream()
                .map(TurmaMapper::toDTO)
                .toList();
    }

    public TurmaResponse findById(Long id) {
        Turma turma = turmaRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID Não existe"));
        return TurmaMapper.toDTO(turma);
    }

    public TurmaResponse save(TurmaRequest turmaRequest) {
        Instrutor instrutor = instrutorRepository.findById(turmaRequest.instrutorId()).orElseThrow(() -> new IdNotFoundException("ID Não existe"));
        Turma save = turmaRepository.save(TurmaMapper.toEntity(turmaRequest, instrutor));
        return TurmaMapper.toDTO(save);
    }

    public TurmaResponse updateById(Long id, TurmaRequest turmaRequest) {
        turmaRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID Não existe"));
        Instrutor instrutor = instrutorRepository.findById(turmaRequest.instrutorId()).orElseThrow(() -> new IdNotFoundException("ID Não existe"));
        Turma entity = TurmaMapper.toEntity(turmaRequest, instrutor);
        instrutor.setId(turmaRequest.instrutorId());
        entity.setId(id);
        Turma save = turmaRepository.save(entity);
        return TurmaMapper.toDTO(save);
    }

    public void deleteById(Long id) {
        turmaRepository.deleteById(id);
    }

}
