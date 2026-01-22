package com.example.Relife_backend.services;

import com.example.Relife_backend.dto.BloodDTO;
import com.example.Relife_backend.entities.BloodEntity;
import com.example.Relife_backend.repositories.BloodRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BloodRequestService {

    private final BloodRepository bloodRepository;
    private final ModelMapper modelMapper;

    private BloodEntity getById(Long id) {
        return bloodRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Blood request not found with id: " + id));
    }

    public BloodDTO create(BloodDTO dto) {
        BloodEntity entity = modelMapper.map(dto, BloodEntity.class);
        BloodEntity saved = bloodRepository.save(entity);
        return modelMapper.map(saved, BloodDTO.class);
    }

    public List<BloodDTO> getAll() {
        return bloodRepository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, BloodDTO.class))
                .toList();
    }

    public void delete(Long id) {
        BloodEntity entity = getById(id);
        bloodRepository.delete(entity);
    }
}
