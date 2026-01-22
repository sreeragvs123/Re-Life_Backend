package com.example.Relife_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodDTO {

    private Long id;   // ✅ ADD THIS

    private String name;

    @NotBlank(message = "Blood group is required")
    private String bloodGroup;

    @NotBlank(message = "Contact information is required")
    private String contact;

    private String city;

    private CreationTimestamp createdAt;
}
