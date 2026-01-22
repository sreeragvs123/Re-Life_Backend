package com.example.Relife_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MissingPersonDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    // ⭐ FIXED: Changed from @NotBlank to @NotNull (Integer can't use @NotBlank)
    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age must be positive")
    @Max(value = 150, message = "Age must be realistic")
    private Integer age;

    // ⭐ FIXED: Changed to match Entity field name
    @NotBlank(message = "Last seen location is required")
    private String lastSeen;

    @NotBlank(message = "Description is required")
    private String description;

    // ⭐ FIXED: Changed to match Flutter field name
    @NotBlank(message = "Family contact is required")
    private String familyContact;

    // ⭐ FIXED: Changed to match Flutter field name
    @NotBlank(message = "Family name is required")
    private String familyName;

    // ⭐ ADDED: Missing field required by Flutter
    private Boolean isFound = false;

    // ⭐ FIXED: Changed from annotation to actual field type
    @CreationTimestamp
    private LocalDateTime createdAt;
}