package com.example.Relife_backend.services;

import com.example.Relife_backend.dto.LoginRequestDTO;
import com.example.Relife_backend.dto.LoginResponseDTO;
import com.example.Relife_backend.dto.SignUpDTO;
import com.example.Relife_backend.dto.UserDTO;
import com.example.Relife_backend.entities.UserEntity;
import com.example.Relife_backend.entities.enums.Role;
import com.example.Relife_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;


    public LoginResponseDTO login(LoginRequestDTO request) {


        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );



        UserEntity user = (UserEntity) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);


        return  new LoginResponseDTO(user.getId(),accessToken,refreshToken);
    }

    public UserDTO signUp(SignUpDTO request) {
        Optional<UserEntity> user = userRepository.findByEmail(request.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("The User Credential " + request.getEmail() +" already Exist");
        }

        //I Think when i map the request to a UserEntity i am automativally createing a UserDetails Bean.
        UserEntity newUser = modelMapper.map(request,UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRole(Role.USER);
        UserEntity savedUser = userRepository.save(newUser);

        return  modelMapper.map(savedUser,UserDTO.class);

    }



    public LoginResponseDTO refreshToken(String refreshToken) {
        Long useId = jwtService.getUserIdFromToken(refreshToken);
        UserEntity user = userService.getUserById(useId);
        String accessToken = jwtService.generateAccessToken(user);

        return  new LoginResponseDTO(user.getId(),accessToken,refreshToken);

    }





    }









