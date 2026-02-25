package com.example.Relife_backend.services;
import com.example.Relife_backend.dto.ShelterDTO;

import java.util.List;

public interface ShelterService {

    ShelterDTO createShelter(ShelterDTO dto);

    List<ShelterDTO> getAllShelters();

    ShelterDTO updateShelter(Long id, ShelterDTO dto);

    void deleteShelter(Long id);
}
