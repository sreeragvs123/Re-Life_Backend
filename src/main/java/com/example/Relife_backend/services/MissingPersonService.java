package com.example.Relife_backend.services;

import com.example.Relife_backend.dto.MissingPersonDTO;
import com.example.Relife_backend.entities.MissingPersonEntity;
import com.example.Relife_backend.repositories.MissingPersonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MissingPersonService {

    private final MissingPersonRepository repository;
    private final ModelMapper modelMapper;

    /* ============================
       CREATE
       ============================ */
    public MissingPersonDTO create(MissingPersonDTO dto) {

        // IMPORTANT: create a NEW entity (TRANSIENT state)
        MissingPersonEntity person =
                modelMapper.map(dto, MissingPersonEntity.class);

        // Hibernate will INSERT because id == null
        MissingPersonEntity saved = repository.save(person);

        return modelMapper.map(saved, MissingPersonDTO.class);
    }

    /* ============================
       UPDATE STATUS
       ============================ */
    public MissingPersonDTO updateStatus(Long id, Boolean isFound) {

        // ALWAYS fetch first (PERSISTENT state)
        MissingPersonEntity person = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Missing person not found with id: " + id)
                );

        // Modify only the required field
        person.setIsFound(isFound);

        // save() optional, but explicit is fine
        MissingPersonEntity updated = repository.save(person);

        return modelMapper.map(updated, MissingPersonDTO.class);
    }

    /* ============================
       DELETE
       ============================ */
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Missing person not found with id: " + id);
        }

        repository.deleteById(id);
    }

    /* ============================
       GET ALL
       ============================ */
    @Transactional(readOnly = true)
    public List<MissingPersonDTO> getAll() {

        return repository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, MissingPersonDTO.class))
                .collect(Collectors.toList());
    }

    /* ============================
       GET BY ID
       ============================ */
    @Transactional(readOnly = true)
    public MissingPersonDTO getById(Long id) {

        MissingPersonEntity person = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Missing person not found with id: " + id)
                );

        return modelMapper.map(person, MissingPersonDTO.class);
    }
}
