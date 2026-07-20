package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Tema;
import com.generation.blogpessoal.repository.TemaRepository;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

    @Autowired
    private TemaRepository temaRepository;

    @GetMapping
    public ResponseEntity<List<Tema>> getAll() {
        return ResponseEntity.ok(temaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tema> getById(@PathVariable Long id) {
        return temaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao) {
        return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    @PostMapping
    public ResponseEntity<Tema> post(@Valid @RequestBody Tema tema) {
        return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
    }

    @PutMapping
    public ResponseEntity<Tema> put(@Valid @RequestBody Tema tema) {
        if (temaRepository.existsById(tema.getId()))
            return ResponseEntity.ok(temaRepository.save(tema));

        // Equivalente: UPDATE tb_temas SET descricao = ? WHERE id = ?;
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Tema> tema = temaRepository.findById(id);

        if (tema.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        temaRepository.deleteById(id);

        // Equivalente: DELETE FROM tb_temas WHERE id = ?;
    }
}
