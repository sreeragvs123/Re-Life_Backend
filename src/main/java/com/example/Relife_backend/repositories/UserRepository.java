package com.example.Relife_backend.repositories;
import com.example.Relife_backend.entities.UserEntity;
import com.example.Relife_backend.entities.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findByEmailAndRole(String email, Role role);
}