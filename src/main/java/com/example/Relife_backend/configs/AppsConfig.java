package com.example.Relife_backend.configs;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// ⚠️ DO NOT nest another @Configuration class inside this one.
// The original code had a nested ModelMapperConfig @Configuration inside
// AppsConfig — Spring may register the outer bean but skip inner beans,
// causing AuthController / AuthService to fail on startup silently.
// All beans must be flat @Bean methods in a single @Configuration class.

@Configuration
public class AppsConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);
        return mapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}