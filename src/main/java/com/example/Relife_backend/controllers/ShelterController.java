package com.example.Relife_backend.controllers;


import com.example.Relife_backend.dto.ShelterDTO;
import com.example.Relife_backend.services.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shelters")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")   // Allow Flutter app on any origin during development
public class ShelterController {

    private final ShelterService shelterService;

    // ── POST /api/shelters ────────────────────────────────────────────────────
    // Admin: create a new shelter
    @PostMapping
    public ResponseEntity<ShelterDTO> createShelter(@RequestBody ShelterDTO dto) {
        ShelterDTO created = shelterService.createShelter(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ── GET /api/shelters ─────────────────────────────────────────────────────
    // All roles: retrieve every shelter
    @GetMapping
    public ResponseEntity<List<ShelterDTO>> getAllShelters() {
        return ResponseEntity.ok(shelterService.getAllShelters());
    }

    // ── PUT /api/shelters/{id} ────────────────────────────────────────────────
    // Admin: update a shelter
    @PutMapping("/{id}")
    public ResponseEntity<ShelterDTO> updateShelter(
            @PathVariable Long id,
            @RequestBody ShelterDTO dto) {
        return ResponseEntity.ok(shelterService.updateShelter(id, dto));
    }

    // ── DELETE /api/shelters/{id} ─────────────────────────────────────────────
    // Admin: delete a shelter (cascades to its routes via JPA)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShelter(@PathVariable Long id) {
        shelterService.deleteShelter(id);
        return ResponseEntity.noContent().build();
    }
}