package com.example.Relife_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationDTO{

    private Long id;

    private String donorName;

    private String contactInfo;

    private String address;

    private String item;

    private Integer quantity;

    private Boolean approved;

    private String status;

    private LocalDateTime createdAt;
}
