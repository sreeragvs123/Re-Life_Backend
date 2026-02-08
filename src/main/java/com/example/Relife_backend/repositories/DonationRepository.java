package com.example.Relife_backend.repositories;


import com.example.Relife_backend.entities.DonationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<DonationEntity, Long> {

    // Find all pending (not approved) donations
    List<DonationEntity> findByIsApprovedFalse();

    // Find all approved donations
    List<DonationEntity> findByIsApprovedTrue();

    // Find donations by donor name
    List<DonationEntity> findByDonorName(String donorName);

    // Find donations by status
    List<DonationEntity> findByStatus(String status);

    // Find approved donations by status
    List<DonationEntity> findByIsApprovedTrueAndStatus(String status);
}