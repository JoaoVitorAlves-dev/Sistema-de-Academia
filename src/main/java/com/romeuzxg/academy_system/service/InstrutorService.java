package com.romeuzxg.academy_system.service;

import com.romeuzxg.academy_system.dto.request.InstrutorRequest;
import com.romeuzxg.academy_system.dto.response.InstrutorResponse;
import com.romeuzxg.academy_system.entity.Instrutor;
import com.romeuzxg.academy_system.exceptions.IdNotFoundException;
import com.romeuzxg.academy_system.mapper.InstrutorMapper;
import com.romeuzxg.academy_system.repository.InstrutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstrutorService {

    private final InstrutorRepository instrutorRepository;

    public List<InstrutorResponse> findAll() {
        return instrutorRepository.findAll().stream()
                .map(InstrutorMapper::toDTO)
                .toList();
    }

    public InstrutorResponse findById(Long id) {
        Instrutor instrutor = instrutorRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID Não existe"));
        return InstrutorMapper.toDTO(instrutor);
    }

    public InstrutorResponse save(InstrutorRequest instrutorRequest) {
        Instrutor save = instrutorRepository.save(InstrutorMapper.toEntity(instrutorRequest));
        return InstrutorMapper.toDTO(save);
    }

    public InstrutorResponse updateById(Long id, InstrutorRequest instrutorRequest) {
        instrutorRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ID Não existe"));
        Instrutor entity = InstrutorMapper.toEntity(instrutorRequest);
        entity.setId(id);
        Instrutor save = instrutorRepository.save(entity);
        return InstrutorMapper.toDTO(save);
    }

    public void deleteById(Long id) {
        instrutorRepository.deleteById(id);
    }

}
