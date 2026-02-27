package com.example.Relife_backend.services;


import com.example.Relife_backend.dto.IssueDTO;

import java.util.List;

public interface IssueService {
    IssueDTO createIssue(IssueDTO dto);
    List<IssueDTO> getAllIssues();
    void deleteIssue(Long id);
}