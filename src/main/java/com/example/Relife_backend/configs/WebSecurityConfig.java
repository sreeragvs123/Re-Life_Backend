package com.example.Relife_backend.configs;

import com.example.Relife_backend.filters.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtFilter jwtFilters;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth

                        // ── Fully public — no JWT needed ───────────────────────────
                        .requestMatchers(
                                "/api/auth/login",
                                "/api/auth/signUp",
                                "/api/auth/refresh",
                                "/api/missing-persons/**"// keep existing public route
                        ).permitAll()

                        // ── Volunteer + Admin only ─────────────────────────────────
                        .requestMatchers(
                                "/api/group-tasks/**",
                                "/api/reports/**"
                        ).hasAnyRole("ADMIN", "VOLUNTEER")

                        // ── Admin only ─────────────────────────────────────────────
                        // Uncomment when you want strict admin-only endpoints:
                        // .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // ── Everything else — any authenticated user ───────────────
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilters, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}