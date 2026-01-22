package com.example.Relife_backend.controllers;

import com.example.Relife_backend.dto.ProductRequestDTO;
import com.example.Relife_backend.services.ProductRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-requests")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductRequestController {

    private final ProductRequestService service;

    @PostMapping("/post")
    public ProductRequestDTO create(@RequestBody @Valid ProductRequestDTO request){
        return service.create(request);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id){
        service.delete(id);
    }

    @GetMapping
    public List<ProductRequestDTO> getAll(){
        return service.getAll();
    }

}
