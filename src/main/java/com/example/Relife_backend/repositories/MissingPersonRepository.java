package com.example.Relife_backend.repositories;

import com.example.Relife_backend.entities.MissingPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingPersonRepository extends JpaRepository<MissingPersonEntity, Long> {

}