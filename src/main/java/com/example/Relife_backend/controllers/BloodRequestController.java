package com.example.Relife_backend.controllers;

import com.example.Relife_backend.dto.BloodDTO;
import com.example.Relife_backend.services.BloodRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blood-requests")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BloodRequestController {

    private final BloodRequestService service;

    @PostMapping
    public BloodDTO createBloodRequest(@RequestBody BloodDTO request) {
        return service.create(request);
    }



}
