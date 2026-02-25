package com.example.Relife_backend.services;



import com.example.Relife_backend.dto.EvacuationRouteDTO;
import com.example.Relife_backend.dto.ShelterDTO;
import com.example.Relife_backend.dto.WaypointDTO;
import com.example.Relife_backend.entities.EvacuationRouteEntity;
import com.example.Relife_backend.entities.ShelterEntity;
import com.example.Relife_backend.repositories.EvacuationRouteRepository;
import com.example.Relife_backend.repositories.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvacuationRouteServiceImpl implements EvacuationRouteService {

    private final EvacuationRouteRepository routeRepository;
    private final ShelterRepository shelterRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;   // Spring auto-configures this

    // ─────────────────────────────────────────────────────────────────────────
    //  CREATE
    // ─────────────────────────────────────────────────────────────────────────
    @Override
    public EvacuationRouteDTO createRoute(EvacuationRouteDTO dto) {
        ShelterEntity shelter = shelterRepository.findById(dto.getShelterId())
                .orElseThrow(() ->
                        new RuntimeException("Shelter not found with id: " + dto.getShelterId()));

        EvacuationRouteEntity route = new EvacuationRouteEntity();
        route.setName(dto.getName());
        route.setShelter(shelter);
        route.setWaypointsJson(toJson(dto.getWaypoints()));

        EvacuationRouteEntity saved = routeRepository.save(route);
        return toDTO(saved);
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  READ ALL
    // ─────────────────────────────────────────────────────────────────────────
    @Override
    public List<EvacuationRouteDTO> getAllRoutes() {
        return routeRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  UPDATE
    // ─────────────────────────────────────────────────────────────────────────
    @Override
    public EvacuationRouteDTO updateRoute(Long id, EvacuationRouteDTO dto) {
        EvacuationRouteEntity existing = routeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Route not found with id: " + id));

        if (dto.getName() != null) {
            existing.setName(dto.getName());
        }

        // Allow re-linking to a different shelter
        if (dto.getShelterId() != null) {
            ShelterEntity shelter = shelterRepository.findById(dto.getShelterId())
                    .orElseThrow(() ->
                            new RuntimeException("Shelter not found with id: " + dto.getShelterId()));
            existing.setShelter(shelter);
        }

        if (dto.getWaypoints() != null) {
            existing.setWaypointsJson(toJson(dto.getWaypoints()));
        }

        EvacuationRouteEntity updated = routeRepository.save(existing);
        return toDTO(updated);
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  DELETE
    // ─────────────────────────────────────────────────────────────────────────
    @Override
    public void deleteRoute(Long id) {
        if (!routeRepository.existsById(id)) {
            throw new RuntimeException("Route not found with id: " + id);
        }
        routeRepository.deleteById(id);
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  HELPERS — Entity ↔ DTO conversion
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Entity → DTO
     * ModelMapper handles the flat fields; we manually wire shelter and waypoints
     * because waypointsJson needs Jackson deserialisation and shelter needs
     * the full nested ShelterDTO (not just an id).
     */
    private EvacuationRouteDTO toDTO(EvacuationRouteEntity entity) {
        EvacuationRouteDTO dto = new EvacuationRouteDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        // Embed full shelter DTO so Flutter gets name + lat/lng in one response
        ShelterDTO shelterDTO = modelMapper.map(entity.getShelter(), ShelterDTO.class);
        dto.setShelter(shelterDTO);
        dto.setShelterId(shelterDTO.getId());

        // Deserialise waypoints JSON → List<WaypointDTO>
        dto.setWaypoints(fromJson(entity.getWaypointsJson()));

        return dto;
    }

    /** Serialise List<WaypointDTO> → JSON string for DB storage */
    private String toJson(List<WaypointDTO> waypoints) {
        if (waypoints == null || waypoints.isEmpty()) return "[]";
        try {
            return objectMapper.writeValueAsString(waypoints);
        } catch (Exception e) {
            return "[]";
        }
    }

    /** Deserialise JSON string from DB → List<WaypointDTO> */
    private List<WaypointDTO> fromJson(String json) {
        if (json == null || json.isBlank()) return Collections.emptyList();
        try {
            return objectMapper.readValue(json, new TypeReference<List<WaypointDTO>>() {});
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}