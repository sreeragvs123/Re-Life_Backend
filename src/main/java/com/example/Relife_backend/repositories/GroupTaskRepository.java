package com.example.Relife_backend.repositories;

import com.example.Relife_backend.entities.GroupTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupTaskRepository extends JpaRepository<GroupTask, Long> {

    // All tasks for a specific place/group — ordered oldest first
    List<GroupTask> findByPlaceOrderByCreatedAtAsc(String place);

    // All tasks — newest first
    List<GroupTask> findAllByOrderByCreatedAtDesc();
}