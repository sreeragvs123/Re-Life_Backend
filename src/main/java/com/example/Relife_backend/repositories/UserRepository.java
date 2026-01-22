package com.example.Relife_backend.repositories;
import com.example.Relife_backend.entities.UserEntity;
import com.example.Relife_backend.entities.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findByEmailAndRole(String email, Role role);
}