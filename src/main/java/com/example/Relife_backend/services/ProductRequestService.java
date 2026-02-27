package com.example.Relife_backend.services;


import com.example.Relife_backend.dto.ProductRequestDTO;
import com.example.Relife_backend.entities.ProductRequest;
import com.example.Relife_backend.repositories.ProductRequestRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductRequestService {

    private final ProductRequestRepository repository;
    private final ModelMapper              modelMapper;


    // ── GET all ───────────────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<ProductRequestDTO> getAllProducts() {
        return repository.findAllByOrderByIdDesc()
                .stream()
                .map(entity -> modelMapper.map(entity, ProductRequestDTO.class))
                .collect(Collectors.toList());
    }

    // ── GET by id ─────────────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public ProductRequestDTO getProductById(Long id) {
        ProductRequest entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return modelMapper.map(entity, ProductRequestDTO.class);
    }

    // ── CREATE ────────────────────────────────────────────────────────────────
    public ProductRequestDTO createProduct(ProductRequestDTO dto) {
        ProductRequest entity = modelMapper.map(dto, ProductRequest.class);
        entity.setId(null); // ensure auto-generation
        ProductRequest saved = repository.save(entity);
        return modelMapper.map(saved, ProductRequestDTO.class);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    public ProductRequestDTO updateProduct(Long id, ProductRequestDTO dto) {
        ProductRequest existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        existing.setName(dto.getName());
        existing.setQuantity(dto.getQuantity());
        existing.setRequester(dto.getRequester());
        existing.setUrgency(dto.getUrgency());
        return modelMapper.map(repository.save(existing), ProductRequestDTO.class);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    public void deleteProduct(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
