package com.example.Relife_backend.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

    private Long   id;
    private String accessToken;
    private String refreshToken;
    private String role;    // "USER" | "VOLUNTEER" | "ADMIN"  — Flutter uses this to route home screens
    private String email;
    private String name;
    private String place;   // volunteer's group/location — null for USER and ADMIN
}