package com.example.Relife_backend.repositories;

import com.example.Relife_backend.entities.EvacuationRouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvacuationRouteRepository extends JpaRepository<EvacuationRouteEntity, Long> {

    // Fetch all routes belonging to a given shelter
    List<EvacuationRouteEntity> findByShelter_Id(Long shelterId);
}