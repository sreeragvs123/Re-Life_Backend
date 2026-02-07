package com.example.Relife_backend.services;

import com.example.Relife_backend.dto.ReportIssueDTO;
import com.example.Relife_backend.entities.ReportIssueEntity;
import com.example.Relife_backend.repositories.ReportIssueRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportIssueService {

    private final ReportIssueRepository repository;
    private final ModelMapper mapper;

    public ReportIssueDTO create(ReportIssueDTO issue) {
        ReportIssueEntity entity = mapper.map(issue, ReportIssueEntity.class);
        ReportIssueEntity savedEntity = repository.save(entity);
        return mapper.map(savedEntity, ReportIssueDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<ReportIssueDTO> getAll() {
        List<ReportIssueEntity> entities = repository.findAll();
        return entities.stream()
                .map(entity -> mapper.map(entity, ReportIssueDTO.class))
                .toList();
    }
}
