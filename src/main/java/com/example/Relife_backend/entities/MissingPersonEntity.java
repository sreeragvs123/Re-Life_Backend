package com.example.Relife_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "missing_persons") // ⭐ ADDED: Explicit table name
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MissingPersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    // ⭐ FIXED: Changed to match DTO field name
    @Column(name = "last_seen", nullable = false)
    private String lastSeen;

    @Column(nullable = false, length = 500)
    private String description;

    // ⭐ FIXED: Changed to match DTO field name
    @Column(name = "family_contact", nullable = false)
    private String familyContact;

    // ⭐ FIXED: Changed field name to camelCase (convention)
    @Column(name = "family_name", nullable = false)
    private String familyName;

    // ⭐ ADDED: Missing field required by Flutter
    @Column(name = "is_found")
    private Boolean isFound = false;


    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}