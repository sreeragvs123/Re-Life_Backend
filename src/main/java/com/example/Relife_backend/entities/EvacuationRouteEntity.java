package com.example.Relife_backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evacuation_routes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvacuationRouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // Many routes can point to the same shelter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shelter_id", nullable = false)
    private ShelterEntity shelter;

    /**
     * Admin-defined intermediate waypoints stored as a JSON array string.
     * Format: [{"latitude":8.9,"longitude":76.6}, ...]
     * These are the points BETWEEN the route start and the shelter.
     * Stored as TEXT — parsed/serialised in the service layer via Jackson.
     */
    @Column(name = "waypoints_json", columnDefinition = "TEXT")
    private String waypointsJson;
}
