package com.example.Relife_backend.services;

import com.example.Relife_backend.dto.ShelterDTO;
import com.example.Relife_backend.entities.ShelterEntity;
import com.example.Relife_backend.repositories.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;
    private final ModelMapper modelMapper;

    @Override
    public ShelterDTO createShelter(ShelterDTO dto) {
        // Map DTO → Entity (id is ignored on create)
        ShelterEntity shelter = modelMapper.map(dto, ShelterEntity.class);
        shelter.setId(null); // safety: never trust client-sent id

        ShelterEntity saved = shelterRepository.save(shelter);
        return modelMapper.map(saved, ShelterDTO.class);
    }

    @Override
    public List<ShelterDTO> getAllShelters() {
        return shelterRepository.findAll()
                .stream()
                .map(s -> modelMapper.map(s, ShelterDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShelterDTO updateShelter(Long id, ShelterDTO dto) {
        ShelterEntity existing = shelterRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Shelter not found with id: " + id));

        // Update only the fields that are present in the DTO
        if (dto.getName()      != null) existing.setName(dto.getName());
        if (dto.getLatitude()  != null) existing.setLatitude(dto.getLatitude());
        if (dto.getLongitude() != null) existing.setLongitude(dto.getLongitude());
        if (dto.getAddress()   != null) existing.setAddress(dto.getAddress());
        if (dto.getCapacity()  != null) existing.setCapacity(dto.getCapacity());

        ShelterEntity updated = shelterRepository.save(existing);
        return modelMapper.map(updated, ShelterDTO.class);
    }

    @Override
    public void deleteShelter(Long id) {
        if (!shelterRepository.existsById(id)) {
            throw new RuntimeException("Shelter not found with id: " + id);
        }
        shelterRepository.deleteById(id);
    }
}
