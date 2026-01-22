package com.example.Relife_backend.repositories;

import com.example.Relife_backend.entities.ProductRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRequestRepository extends JpaRepository<ProductRequestEntity, Long> {
}