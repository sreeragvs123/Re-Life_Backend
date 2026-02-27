package com.example.Relife_backend.dto;

import com.example.Relife_backend.entities.enums.Role;
import com.example.Relife_backend.entities.enums.Urgency;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @NotBlank(message = "Requester name is required")
    private String requester;

    @NotBlank(message = "Urgency is required")
    @Pattern(regexp = "High|Medium|Low", message = "Urgency must be High, Medium, or Low")
    private String urgency;
}
