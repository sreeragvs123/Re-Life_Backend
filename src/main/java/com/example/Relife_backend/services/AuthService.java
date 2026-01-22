package com.example.Relife_backend.services;

import com.example.Relife_backend.configs.JwtUtil;
import com.example.Relife_backend.dto.LoginRequestDTO;
import com.example.Relife_backend.dto.LoginResponseDTO;
import com.example.Relife_backend.dto.UserDTO;
import com.example.Relife_backend.entities.UserEntity;
import com.example.Relife_backend.entities.enums.Role;
import com.example.Relife_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public LoginResponseDTO login(LoginRequestDTO request) {

        Role role = Role.valueOf(request.getRole());

        UserEntity userEntity = userRepository
                .findByEmailAndRole(request.getEmail(), role)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);

        if (!userDTO.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = JwtUtil.generateToken(userDTO.getEmail(), role.name());

        return new LoginResponseDTO(token, role.name(), userDTO.getEmail());
    }
}

