package com.example.Relife_backend.repositories;

import com.example.Relife_backend.entities.BloodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodRepository extends JpaRepository<BloodEntity, Long> {
}