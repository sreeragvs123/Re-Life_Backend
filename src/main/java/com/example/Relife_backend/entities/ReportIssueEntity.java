package com.example.Relife_backend.entities;


import com.example.Relife_backend.entities.enums.IssueCategory;
import com.example.Relife_backend.entities.enums.Urgency;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportIssueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String contactInfo;

    private String Title;

    private String Description;

    private Urgency priority;

    private IssueCategory issue;

    private String location;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
