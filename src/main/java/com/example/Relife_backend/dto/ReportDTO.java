package com.example.Relife_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private Long id;
    private String volunteerName;
    private String group;
    private String task;
    private String description;
    private LocalDateTime date;
}