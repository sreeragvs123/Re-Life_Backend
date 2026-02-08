package com.example.Relife_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationDTO {

    private Long id;

    @NotBlank(message = "Donor name is required")
    private String donorName;

    @NotBlank(message = "Contact is required")
    private String contact;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Item is required")
    private String item;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    private LocalDateTime date;

    private Boolean isApproved = false;

    private String status = "Pending";
}