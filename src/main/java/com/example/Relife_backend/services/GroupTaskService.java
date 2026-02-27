package com.example.Relife_backend.services;


import com.example.Relife_backend.dto.GroupTaskDTO;
import com.example.Relife_backend.entities.GroupTask;
import com.example.Relife_backend.repositories.GroupTaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupTaskService {

    private final GroupTaskRepository repository;
    private final ModelMapper         modelMapper;


    // ── GET all ───────────────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<GroupTaskDTO> getAllTasks() {
        return repository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(entity -> modelMapper.map(entity, GroupTaskDTO.class))
                .collect(Collectors.toList());
    }

    // ── GET by place ──────────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<GroupTaskDTO> getTasksByPlace(String place) {
        return repository.findByPlaceOrderByCreatedAtAsc(place)
                .stream()
                .map(entity -> modelMapper.map(entity, GroupTaskDTO.class))
                .collect(Collectors.toList());
    }

    // ── CREATE ────────────────────────────────────────────────────────────────
    public GroupTaskDTO createTask(GroupTaskDTO dto) {
        GroupTask entity = modelMapper.map(dto, GroupTask.class);
        entity.setId(null); // ensure auto-generation
        GroupTask saved = repository.save(entity);
        return modelMapper.map(saved, GroupTaskDTO.class);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    public void deleteTask(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        repository.deleteById(id);
    }
}