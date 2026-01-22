package com.example.Relife_backend.services;

import com.example.Relife_backend.dto.MissingPersonDTO;
import com.example.Relife_backend.entities.MissingPersonEntity;
import com.example.Relife_backend.repositories.MissingPersonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissingPersonService {

    private final MissingPersonRepository repository;
    private final ModelMapper mapper;

    public MissingPersonDTO create(MissingPersonDTO person) {
        MissingPersonEntity entity = mapper.map(person, MissingPersonEntity.class);
        MissingPersonEntity savedEntity = repository.save(entity);
        return mapper.map(savedEntity, MissingPersonDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<MissingPersonDTO> getAll() {
        List<MissingPersonEntity> entities = repository.findAll();
        return entities.stream()
                .map(entity -> mapper.map(entity, MissingPersonDTO.class))
                .toList();
    }
}
