package com.example.Relife_backend.repositories;


import com.example.Relife_backend.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByVolunteerName(String volunteerName);
}