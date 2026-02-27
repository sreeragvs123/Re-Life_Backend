package com.example.Relife_backend.services;

import com.example.Relife_backend.dto.IssueDTO;
import com.example.Relife_backend.entities.Issue;
import com.example.Relife_backend.repositories.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;

    @Override
    public IssueDTO createIssue(IssueDTO dto) {
        Issue issue = modelMapper.map(dto, Issue.class);
        issue.setId(null);
        return modelMapper.map(issueRepository.save(issue), IssueDTO.class);
    }

    @Override
    public List<IssueDTO> getAllIssues() {
        return issueRepository.findAll().stream()
                .map(i -> modelMapper.map(i, IssueDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteIssue(Long id) {
        issueRepository.deleteById(id);
    }
}