package com.example.Relife_backend.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponseDTO {

    private Long id;
    private String accessToken;
    private String refreshToken;

}
