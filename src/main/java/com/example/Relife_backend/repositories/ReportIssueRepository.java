package com.example.Relife_backend.repositories;

import com.example.Relife_backend.entities.ReportIssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportIssueRepository extends JpaRepository<ReportIssueEntity, Long> {
}