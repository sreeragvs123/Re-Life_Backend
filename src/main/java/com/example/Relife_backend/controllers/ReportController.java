package com.example.Relife_backend.controllers;

import com.example.Relife_backend.dto.ReportDTO;
import com.example.Relife_backend.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<ReportDTO> createReport(@RequestBody ReportDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reportService.createReport(dto));
    }

    @GetMapping
    public ResponseEntity<List<ReportDTO>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    // GET /api/reports/volunteer?volunteerName=John
    @GetMapping("/volunteer")
    public ResponseEntity<List<ReportDTO>> getByVolunteer(
            @RequestParam String volunteerName) {
        return ResponseEntity.ok(reportService.getReportsByVolunteer(volunteerName));
    }
}