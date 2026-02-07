package com.example.Relife_backend.controllers;

import com.example.Relife_backend.dto.ReportIssueDTO;
import com.example.Relife_backend.services.ReportIssueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report-issues")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ReportIssueController {

    private final ReportIssueService service;

    @PostMapping("/post")
    public ReportIssueDTO create(@RequestBody @Valid ReportIssueDTO issue) {
        return service.create(issue);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        service.delete(id);
    }

    @GetMapping
    public List<ReportIssueDTO> getAll() {
        return service.getAll();
    }
}
