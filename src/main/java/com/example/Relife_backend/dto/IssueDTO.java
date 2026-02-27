package com.example.Relife_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String title;
    private String description;
    private String category;
    private String priority;
    private String location;
    private String attachmentBase64;
    private LocalDateTime date;
}