package com.example.Relife_backend.controllers;

import com.example.Relife_backend.dto.*;
import com.example.Relife_backend.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // ── POST /api/auth/signUp ──────────────────────────────────────────────────
    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@Valid @RequestBody SignUpDTO request) {
        return ResponseEntity.ok(authService.signUp(request));
    }

    // ── POST /api/auth/login ───────────────────────────────────────────────────
    // FIX: Removed HttpOnly cookie — cookies don't work in Flutter mobile apps.
    // refreshToken is already in the response body (LoginResponseDTO.refreshToken)
    // so Flutter Dio can read it directly from JSON. No cookie needed.
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO login = authService.login(request);
        return ResponseEntity.ok(login);
    }

    // ── POST /api/auth/refresh ─────────────────────────────────────────────────
    // FIX: Read refreshToken from JSON body instead of HttpOnly cookie.
    // Cookies are a browser mechanism — Flutter sends JSON, not cookies.
    //
    // Request body: { "refreshToken": "<token>" }
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(
            @RequestBody Map<String, String> body) {

        String refreshToken = body.get("refreshToken");
        if (refreshToken == null || refreshToken.isBlank()) {
            throw new AuthenticationServiceException("refreshToken is required in request body");
        }

        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }
}