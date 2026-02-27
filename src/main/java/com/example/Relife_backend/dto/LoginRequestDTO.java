package com.example.Relife_backend.dto;



import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    private String email;
    private String password;
    // No role field — role comes from the DB after authentication.
    // Accepting role from client is a security risk.
}