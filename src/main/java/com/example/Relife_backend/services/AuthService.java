package com.example.Relife_backend.services;

import com.example.Relife_backend.dto.*;
import com.example.Relife_backend.entities.UserEntity;
import com.example.Relife_backend.entities.enums.Role;
import com.example.Relife_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository        userRepository;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper           modelMapper;
    private final JwtService            jwtService;
    private final PasswordEncoder       passwordEncoder;
    private final UserService           userService;

    // ── Login ─────────────────────────────────────────────────────────────────
    public LoginResponseDTO login(LoginRequestDTO request) {

        // Spring Security validates email + password via UserService.loadUserByUsername
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));

        UserEntity user = (UserEntity) authentication.getPrincipal();

        String accessToken  = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResponseDTO(
                user.getId(),
                accessToken,
                refreshToken,
                user.getRole().name(),   // "USER" | "VOLUNTEER" | "ADMIN"
                user.getEmail(),
                user.getName(),
                user.getPlace()          // null for USER/ADMIN, group location for VOLUNTEER
        );
    }

    // ── Sign Up ───────────────────────────────────────────────────────────────
    public UserDTO signUp(SignUpDTO request) {

        Optional<UserEntity> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            throw new BadCredentialsException(
                    "Email already registered: " + request.getEmail());
        }

        UserEntity newUser = modelMapper.map(request, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        // Only USER or VOLUNTEER can self-register — ADMIN is never allowed
        Role assignedRole = Role.USER;
        if ("VOLUNTEER".equalsIgnoreCase(request.getRole())) {
            assignedRole = Role.VOLUNTEER;
        }
        newUser.setRole(assignedRole);

        UserEntity saved = userRepository.save(newUser);
        return modelMapper.map(saved, UserDTO.class);
    }

    // ── Refresh Token ─────────────────────────────────────────────────────────
    public LoginResponseDTO refreshToken(String refreshToken) {

        Long userId = jwtService.getUserIdFromToken(refreshToken);
        UserEntity user = userService.getUserById(userId);
        String newAccessToken = jwtService.generateAccessToken(user);

        return new LoginResponseDTO(
                user.getId(),
                newAccessToken,
                refreshToken,
                user.getRole().name(),
                user.getEmail(),
                user.getName(),
                user.getPlace()
        );
    }
}