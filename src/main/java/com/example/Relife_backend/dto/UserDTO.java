package com.example.Relife_backend.dto;

import com.example.Relife_backend.entities.enums.Role;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long   id;
    private String name;
    private String email;
    private String place;
    private Role   role;
    // No password field — never send password back to client
}