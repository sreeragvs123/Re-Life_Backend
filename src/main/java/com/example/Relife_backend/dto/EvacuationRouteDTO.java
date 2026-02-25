package com.example.Relife_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvacuationRouteDTO {

    private Long id;                      // null on create requests

    private String name;

    // ── Inbound (create / update) ────────────────────────────────────────────
    // Client sends shelterId to link the route to an existing shelter
    private Long shelterId;

    // ── Outbound (read) ──────────────────────────────────────────────────────
    // Full shelter object embedded in response so Flutter can read
    // shelterName, latitude, longitude in one call
    private ShelterDTO shelter;

    // Waypoints list — serialised to/from waypointsJson in the entity
    private List<WaypointDTO> waypoints;
}
