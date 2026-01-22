package com.example.Relife_backend.dto;

import com.example.Relife_backend.entities.enums.Role;
import com.example.Relife_backend.entities.enums.Urgency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    private Long id;
    private String name;
    private Integer quantity;
    private Urgency urgency;
    private Role role;
    private CreationTimestamp createdAt;
}
