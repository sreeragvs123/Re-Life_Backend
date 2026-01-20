package com.example.Relife_backend.repositories;


import java.util.Optional;

import com.example.Relife_backend.entities.UserDTO;
import com.example.Relife_backend.entities.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {

    Optional<UserDTO> findByEmailAndRole(String email, Role role);
}