package com.example.Relife_backend.configs;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
public class AppsConfig {




        @Configuration
        public class ModelMapperConfig {

            @Bean
            public ModelMapper modelMapper() {
                ModelMapper mapper = new ModelMapper();

                // STRICT matching prevents accidental cross-field mappings when entity
                // and DTO field names partially overlap (e.g., shelter.id vs route.id)
                mapper.getConfiguration()
                        .setMatchingStrategy(MatchingStrategies.STRICT)
                        .setSkipNullEnabled(true);  // skip null source fields on update

                // ── Shelter ↔ ShelterDTO ──────────────────────────────────────────────
                // No custom TypeMap needed — all field names match exactly, so
                // ModelMapper handles this automatically with STRICT strategy.

                // ── EvacuationRoute → EvacuationRouteDTO ─────────────────────────────
                // NO TypeMap registered here intentionally.
                // EvacuationRouteServiceImpl.toDTO() does all the mapping manually
                // because:
                //   1. The nested Shelter object needs to become a flat ShelterDTO
                //   2. waypointsJson (String) needs Jackson to deserialise to List<WaypointDTO>
                // Registering a TypeMap here caused ModelMapper to auto-discover the
                // nested shelter.* fields before our skip() calls, which ModelMapper
                // 2.x+ forbids — hence the startup error.

                return mapper;
            }
        }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
