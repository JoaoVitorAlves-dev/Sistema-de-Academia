package com.romeuzxg.academy_system.controller;

import com.romeuzxg.academy_system.dto.request.InstrutorRequest;
import com.romeuzxg.academy_system.dto.response.InstrutorResponse;
import com.romeuzxg.academy_system.service.InstrutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrutores")
@RequiredArgsConstructor
public class InstrutorController {

    private final InstrutorService instrutorService;

    @GetMapping
    public ResponseEntity<List<InstrutorResponse>> findAll() {
        return ResponseEntity.ok(instrutorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrutorResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(instrutorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<InstrutorResponse> save(@RequestBody InstrutorRequest instrutorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(instrutorService.save(instrutorRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstrutorResponse> updateById(@PathVariable Long id, @RequestBody InstrutorRequest instrutorRequest) {
        return ResponseEntity.ok(instrutorService.updateById(id, instrutorRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        instrutorService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
