package com.example.Relife_backend.controllers;

import com.example.Relife_backend.dto.*;
import com.example.Relife_backend.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO request,
            HttpServletResponse response) {

        LoginResponseDTO login = authService.login(request);

        // Store refreshToken in HttpOnly cookie (more secure than body)
        Cookie cookie = new Cookie("refreshToken", login.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(login);
    }

    // ── POST /api/auth/refresh ─────────────────────────────────────────────────
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new AuthenticationServiceException("No cookies found");
        }

        String refreshToken = Arrays.stream(cookies)
                .filter(c -> "refreshToken".equals(c.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException(
                        "Refresh token not found in cookie"));

        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }
}