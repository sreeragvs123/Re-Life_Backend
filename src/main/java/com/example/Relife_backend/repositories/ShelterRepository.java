package com.example.Relife_backend.repositories;

import com.example.Relife_backend.entities.ShelterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends JpaRepository<ShelterEntity, Long> {
}