package com.romeuzxg.academy_system.controller;

import com.romeuzxg.academy_system.dto.request.TurmaRequest;
import com.romeuzxg.academy_system.dto.response.TurmaResponse;
import com.romeuzxg.academy_system.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<TurmaResponse>> findAll() {
        return ResponseEntity.ok(turmaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(turmaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TurmaResponse> save(@RequestBody TurmaRequest turmaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaService.save(turmaRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponse> updateById(@PathVariable Long id, @RequestBody TurmaRequest turmaRequest) {
        return ResponseEntity.ok(turmaService.updateById(id, turmaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        turmaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
