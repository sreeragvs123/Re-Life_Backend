package com.example.Relife_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShelterDTO {

    private Long id;                 // null on create requests

    private String name;

    private Double latitude;

    private Double longitude;

    private String address;          // optional

    private Integer capacity;        // optional
}