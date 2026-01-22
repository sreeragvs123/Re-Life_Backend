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
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity/*object use to describe how HTTP requests should be secured.*/) throws Exception{
        httpSecurity
                //This block defines authorization rules,ie which endpoints are public and which require authentication
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/posts","/api/**","/api/auth/signUp","/api/auth/**","/api/missing-persons/**").permitAll()//for this endpoint,permit all to access without authentication ie,controller will be called directly
                        //.requestMatchers("/post/**").hasAnyRole("USER","ADMIN")//for this endpoint,user must have either USER or ADMIN role to access
                        .anyRequest().authenticated())//All other requests require authentication before access is granted
                //Disables CSRF protection,
                .csrf(csrf->csrf.disable())
                //STATELESS means server will not store any session info about client between requests
                .sessionManagement(Session->Session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilters, UsernamePasswordAuthenticationFilter.class);
        //enables form-based authentication in Spring Security.


        return httpSecurity.build();//Take everything that is configured and turn it into actual security filters;
    }



    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}
