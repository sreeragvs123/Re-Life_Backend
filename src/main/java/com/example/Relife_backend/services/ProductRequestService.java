package com.example.Relife_backend.services;

import com.example.Relife_backend.dto.ProductRequestDTO;
import com.example.Relife_backend.entities.ProductRequestEntity;
import com.example.Relife_backend.repositories.ProductRequestRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRequestService {
    private final ProductRequestRepository repository;
    private final ModelMapper mapper;

    public ProductRequestDTO create(ProductRequestDTO request) {
        ProductRequestEntity entity = mapper.map(request, ProductRequestEntity.class);
        ProductRequestEntity savedEntity = repository.save(entity);
        return mapper.map(savedEntity, ProductRequestDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<ProductRequestDTO> getAll() {
        List<ProductRequestEntity> entities = repository.findAll();
        return entities.stream()
                .map(entity -> mapper.map(entity, ProductRequestDTO.class))
                .toList();
    }
}
