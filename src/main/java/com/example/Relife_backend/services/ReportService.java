package com.example.Relife_backend.services;


import com.example.Relife_backend.dto.ReportDTO;

import java.util.List;

public interface ReportService {
    ReportDTO createReport(ReportDTO dto);
    List<ReportDTO> getAllReports();
    List<ReportDTO> getReportsByVolunteer(String volunteerName);
}