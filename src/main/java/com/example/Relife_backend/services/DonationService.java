package com.example.Relife_backend.services;

import com.example.Relife_backend.dto.DonationDTO;
import com.example.Relife_backend.entities.DonationEntity;
import com.example.Relife_backend.repositories.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository repository;
    private final ModelMapper modelMapper;

    // Create a new donation
    @Transactional
    public DonationDTO create(DonationDTO dto) {
        // Map DTO to Entity
        DonationEntity entity = modelMapper.map(dto, DonationEntity.class);

        // Set initial values
        entity.setDate(LocalDateTime.now());
        entity.setIsApproved(false);
        entity.setStatus("Pending");

        // Save entity
        DonationEntity saved = repository.save(entity);

        // Map entity back to DTO and return
        return modelMapper.map(saved, DonationDTO.class);
    }

    // Get all donations
    public List<DonationDTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, DonationDTO.class))
                .collect(Collectors.toList());
    }

    // Get pending donations (not approved yet)
    public List<DonationDTO> getPendingDonations() {
        return repository.findByIsApprovedFalse().stream()
                .map(entity -> modelMapper.map(entity, DonationDTO.class))
                .collect(Collectors.toList());
    }

    // Get approved donations
    public List<DonationDTO> getApprovedDonations() {
        return repository.findByIsApprovedTrue().stream()
                .map(entity -> modelMapper.map(entity, DonationDTO.class))
                .collect(Collectors.toList());
    }

    // Get donations by donor name
    public List<DonationDTO> getDonationsByDonor(String donorName) {
        return repository.findByDonorName(donorName).stream()
                .map(entity -> modelMapper.map(entity, DonationDTO.class))
                .collect(Collectors.toList());
    }

    // Approve a donation
    @Transactional
    public DonationDTO approveDonation(Long id) {
        DonationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found with id: " + id));

        entity.setIsApproved(true);
        entity.setStatus("Approved");

        DonationEntity updated = repository.save(entity);
        return modelMapper.map(updated, DonationDTO.class);
    }

    // Update donation status
    @Transactional
    public DonationDTO updateStatus(Long id, String status) {
        DonationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found with id: " + id));

        // Validate status
        if (!isValidStatus(status)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

        entity.setStatus(status);

        DonationEntity updated = repository.save(entity);
        return modelMapper.map(updated, DonationDTO.class);
    }

    // Delete a donation
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Donation not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // Validate status
    private boolean isValidStatus(String status) {
        return status.equals("Pending") ||
                status.equals("Approved") ||
                status.equals("On the way") ||
                status.equals("Delivered");
    }
}