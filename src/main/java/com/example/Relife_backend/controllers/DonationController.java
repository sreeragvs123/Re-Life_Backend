package com.example.Relife_backend.controllers;

import com.example.Relife_backend.dto.DonationDTO;
import com.example.Relife_backend.services.DonationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService service;

    /**
     * Create a new donation (User)
     * POST /api/donations/post
     */
    @PostMapping("/post")
    public ResponseEntity<DonationDTO> create(@RequestBody @Valid DonationDTO donation) {
        DonationDTO created = service.create(donation);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Get all donations
     * GET /api/donations
     */
    @GetMapping
    public ResponseEntity<List<DonationDTO>> getAll(
            @RequestParam(required = false) String donorName) {
        if (donorName != null && !donorName.isEmpty()) {
            return ResponseEntity.ok(service.getDonationsByDonor(donorName));
        }
        return ResponseEntity.ok(service.getAll());
    }

    /**
     * Get pending donations (Admin)
     * GET /api/donations/pending
     */
    @GetMapping("/pending")
    public ResponseEntity<List<DonationDTO>> getPendingDonations() {
        return ResponseEntity.ok(service.getPendingDonations());
    }

    /**
     * Get approved donations (Volunteer & User)
     * GET /api/donations/approved
     */
    @GetMapping("/approved")
    public ResponseEntity<List<DonationDTO>> getApprovedDonations() {
        return ResponseEntity.ok(service.getApprovedDonations());
    }

    /**
     * Approve a donation (Admin)
     * PUT /api/donations/approve/{id}
     */
    @PutMapping("/approve/{id}")
    public ResponseEntity<DonationDTO> approveDonation(@PathVariable Long id) {
        DonationDTO approved = service.approveDonation(id);
        return ResponseEntity.ok(approved);
    }

    /**
     * Update donation status (Volunteer)
     * PUT /api/donations/status/{id}?status={status}
     */
    @PutMapping("/status/{id}")
    public ResponseEntity<DonationDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        DonationDTO updated = service.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    /**
     * Delete/Reject a donation (Admin)
     * DELETE /api/donations/delete?id={id}
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}