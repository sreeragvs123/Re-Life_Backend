package com.example.Relife_backend.services;

import com.example.Relife_backend.dto.DonationDTO;
import com.example.Relife_backend.entities.DonationEntity;
import com.example.Relife_backend.repositories.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository repository;
    private final ModelMapper mapper;

    public DonationDTO create(DonationDTO donation) {

        // 🔒 Force INSERT
        donation.setId(null);
        donation.setApproved(false);
        donation.setStatus("Pending");

        DonationEntity entity = mapper.map(donation, DonationEntity.class);
        DonationEntity saved = repository.save(entity);

        return mapper.map(saved, DonationDTO.class);
    }

    public List<DonationDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(e -> mapper.map(e, DonationDTO.class))
                .toList();
    }

    public DonationDTO approve(Long id) {
        DonationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found"));

        entity.setApproved(true);
        entity.setStatus("Approved");

        return mapper.map(repository.save(entity), DonationDTO.class);
    }

    public DonationDTO updateStatus(Long id, String status) {
        DonationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found"));

        entity.setStatus(status);
        return mapper.map(repository.save(entity), DonationDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
