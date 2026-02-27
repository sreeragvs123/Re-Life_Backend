package com.example.Relife_backend.controllers;

import com.example.Relife_backend.dto.ProductRequestDTO;
import com.example.Relife_backend.services.ProductRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*") // restrict in production
@RequiredArgsConstructor
public class ProductRequestController {

    private final ProductRequestService service;


    // ── GET /api/products ──────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<ProductRequestDTO>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    // ── GET /api/products/{id} ─────────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<ProductRequestDTO> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    // ── POST /api/products ─────────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<ProductRequestDTO> createProduct(
            @Valid @RequestBody ProductRequestDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createProduct(dto));
    }

    // ── PUT /api/products/{id} ─────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<ProductRequestDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO dto) {
        return ResponseEntity.ok(service.updateProduct(id, dto));
    }

    // ── DELETE /api/products/{id} ──────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}