package com.example.Relife_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupTaskDTO {

    private Long id;

    @NotBlank(message = "Place is required")
    private String place;

    @NotBlank(message = "Task description is required")
    private String task;

    private LocalDateTime createdAt;
}
