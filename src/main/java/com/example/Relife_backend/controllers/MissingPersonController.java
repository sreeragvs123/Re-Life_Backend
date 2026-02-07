package com.example.Relife_backend.controllers;

import com.example.Relife_backend.dto.MissingPersonDTO;
import com.example.Relife_backend.services.MissingPersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map; // ⭐ ADDED: Missing import

@RestController
@RequestMapping("/api/missing-persons")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MissingPersonController {

    private final MissingPersonService service;

    @PostMapping("/post")
    public ResponseEntity<MissingPersonDTO> create(@RequestBody @Valid MissingPersonDTO person) {
        MissingPersonDTO created = service.create(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/update")
    public ResponseEntity<MissingPersonDTO> updateStatus(
            @RequestParam Long id,
            @RequestBody Map<String, Boolean> request) {
        Boolean isFound = request.get("isFound");
        MissingPersonDTO updated = service.updateStatus(id, isFound);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MissingPersonDTO>> getAll() {
        List<MissingPersonDTO> persons = service.getAll();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MissingPersonDTO> getById(@PathVariable Long id) {
        MissingPersonDTO person = service.getById(id);
        return ResponseEntity.ok(person);
    }
}