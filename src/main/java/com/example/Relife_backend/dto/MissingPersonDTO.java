package com.example.Relife_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MissingPersonDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Age is required")
    private Integer age;

    private String lastKnownLocation;

    private String description;

    @NotBlank(message = "Contact Info is required")
    private String contactInfo;

    private String FamilyName;

    private CreationTimestamp createdAt;
}
