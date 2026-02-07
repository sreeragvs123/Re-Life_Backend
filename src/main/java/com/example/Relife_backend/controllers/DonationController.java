package com.example.Relife_backend.controllers;

import com.example.Relife_backend.dto.DonationDTO;
import com.example.Relife_backend.services.DonationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService service;

    @PostMapping("/post")
    public DonationDTO create(@RequestBody @Valid DonationDTO donation) {
        return service.create(donation);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        service.delete(id);

    }

    @GetMapping
    public List<DonationDTO> getAll() {
        return service.getAll();
    }

}