package com.example.Relife_backend.controllers;

import com.example.Relife_backend.dto.EvacuationRouteDTO;
import com.example.Relife_backend.services.EvacuationRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evacuation-routes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")   // Allow Flutter app on any origin during development
public class EvacuationRouteController {

    private final EvacuationRouteService routeService;

    // ── POST /api/evacuation-routes ───────────────────────────────────────────
    // Admin: create a new evacuation route linked to an existing shelter
    @PostMapping
    public ResponseEntity<EvacuationRouteDTO> createRoute(
            @RequestBody EvacuationRouteDTO dto) {
        EvacuationRouteDTO created = routeService.createRoute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ── GET /api/evacuation-routes ────────────────────────────────────────────
    // All roles: fetch all routes with embedded shelter info
    @GetMapping
    public ResponseEntity<List<EvacuationRouteDTO>> getAllRoutes() {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }

    // ── PUT /api/evacuation-routes/{id} ───────────────────────────────────────
    // Admin: update a route (name, shelter link, or waypoints)
    @PutMapping("/{id}")
    public ResponseEntity<EvacuationRouteDTO> updateRoute(
            @PathVariable Long id,
            @RequestBody EvacuationRouteDTO dto) {
        return ResponseEntity.ok(routeService.updateRoute(id, dto));
    }

    // ── DELETE /api/evacuation-routes/{id} ────────────────────────────────────
    // Admin: delete a single route (shelter is NOT deleted here)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}