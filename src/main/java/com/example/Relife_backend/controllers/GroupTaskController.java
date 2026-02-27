package com.example.Relife_backend.controllers;


import com.example.Relife_backend.dto.GroupTaskDTO;
import com.example.Relife_backend.services.GroupTaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group-tasks")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class GroupTaskController {

    private final GroupTaskService service;



    // ── GET /api/group-tasks ───────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<GroupTaskDTO>> getAllTasks() {
        return ResponseEntity.ok(service.getAllTasks());
    }

    // ── GET /api/group-tasks/place/{place} ────────────────────────────────────
    @GetMapping("/place/{place}")
    public ResponseEntity<List<GroupTaskDTO>> getTasksByPlace(
            @PathVariable String place) {
        return ResponseEntity.ok(service.getTasksByPlace(place));
    }

    // ── POST /api/group-tasks ──────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<GroupTaskDTO> createTask(
            @Valid @RequestBody GroupTaskDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createTask(dto));
    }

    // ── DELETE /api/group-tasks/{id} ──────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}