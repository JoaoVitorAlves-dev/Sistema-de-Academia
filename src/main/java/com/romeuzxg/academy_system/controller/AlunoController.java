package com.romeuzxg.academy_system.controller;

import com.romeuzxg.academy_system.dto.request.AlunoRequest;
import com.romeuzxg.academy_system.dto.response.AlunoResponse;
import com.romeuzxg.academy_system.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> findAll() {
        return ResponseEntity.ok(alunoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> save(@RequestBody AlunoRequest alunoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.save(alunoRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> updateById(@PathVariable Long id, @RequestBody AlunoRequest alunoRequest) {
        return ResponseEntity.ok(alunoService.updateById(id, alunoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        alunoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
