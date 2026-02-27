package com.example.Relife_backend.repositories;

import com.example.Relife_backend.entities.ProductRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRequestRepository extends JpaRepository<ProductRequest, Long> {

    // Filter by urgency level — used if you want server-side filtering later
    List<ProductRequest> findByUrgencyOrderByIdDesc(String urgency);

    // All sorted newest-first
    List<ProductRequest> findAllByOrderByIdDesc();
}