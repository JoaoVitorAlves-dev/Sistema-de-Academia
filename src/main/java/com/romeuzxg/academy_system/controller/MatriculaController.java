package com.romeuzxg.academy_system.controller;

import com.romeuzxg.academy_system.dto.request.MatriculaRequest;
import com.romeuzxg.academy_system.dto.response.MatriculaResponse;
import com.romeuzxg.academy_system.service.MatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<MatriculaResponse>> findAll() {
        return ResponseEntity.ok(matriculaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MatriculaResponse> save(@RequestBody MatriculaRequest matriculaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaService.save(matriculaRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaResponse> updateById(@PathVariable Long id, @RequestBody MatriculaRequest matriculaRequest) {
        return ResponseEntity.ok(matriculaService.updateById(id, matriculaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        matriculaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
