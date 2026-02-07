package com.example.Relife_backend.dto;


import com.example.Relife_backend.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    //This will be the common signUp and the Volunteer DTO
    private String email;
    private String name;
    private String password;
    private String place;

}
