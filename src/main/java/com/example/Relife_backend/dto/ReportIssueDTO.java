package com.example.Relife_backend.dto;

import com.example.Relife_backend.entities.enums.IssueCategory;
import com.example.Relife_backend.entities.enums.Urgency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportIssueDTO {

    private Long id;

    private String name;

    private String email;

    private String contactInfo;

    private String Title;

    private String Description;

    private Urgency priority;

    private IssueCategory issue;

    private String location;

    private CreationTimestamp createdAt;
}
