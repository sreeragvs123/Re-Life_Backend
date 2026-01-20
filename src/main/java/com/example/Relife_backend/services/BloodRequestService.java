package com.example.Relife_backend.services;

import com.example.Relife_backend.dto.BloodDTO;
import com.example.Relife_backend.entities.BloodEntity;
import com.example.Relife_backend.repositories.BloodRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BloodRequestService {

    private final BloodRepository bloodRepository;
    private final ModelMapper modelMapper;

    public BloodDTO create(BloodDTO request) {
        BloodEntity toSave = modelMapper.map(request, BloodEntity.class);
        BloodEntity saved = bloodRepository.save(toSave);
        return modelMapper.map(saved, BloodDTO.class);
    }

}