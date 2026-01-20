package com.example.Relife_backend.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponseDTO {
    private String token;
    private String role;
    private String email;

}
