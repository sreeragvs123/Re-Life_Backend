package com.example.Relife_backend.controllers;

import com.example.Relife_backend.dto.BloodDTO;
import com.example.Relife_backend.services.BloodRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-requests")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BloodRequestController {

    private final BloodRequestService service;

    @PostMapping("/post")
    public BloodDTO create(@RequestBody @Valid BloodDTO request) {
        return service.create(request);
    }

    @GetMapping
    public List<BloodDTO> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        service.delete(id);
    }

}
