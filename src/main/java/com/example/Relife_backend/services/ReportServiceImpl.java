package com.example.Relife_backend.services;

import com.example.Relife_backend.dto.ReportDTO;
import com.example.Relife_backend.entities.Report;
import com.example.Relife_backend.repositories.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;

    @Override
    public ReportDTO createReport(ReportDTO dto) {
        Report report = modelMapper.map(dto, Report.class);
        report.setId(null);
        return modelMapper.map(reportRepository.save(report), ReportDTO.class);
    }

    @Override
    public List<ReportDTO> getAllReports() {
        return reportRepository.findAll().stream()
                .map(r -> modelMapper.map(r, ReportDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReportDTO> getReportsByVolunteer(String volunteerName) {
        return reportRepository.findByVolunteerName(volunteerName).stream()
                .map(r -> modelMapper.map(r, ReportDTO.class))
                .collect(Collectors.toList());
    }
}