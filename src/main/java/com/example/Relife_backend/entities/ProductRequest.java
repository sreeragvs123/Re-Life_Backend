package com.example.Relife_backend.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_requests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Min(1)
    @Column(nullable = false)
    private int quantity;

    @NotBlank
    @Column(nullable = false)
    private String requester;

    @NotBlank
    @Column(nullable = false)
    private String urgency; // "High" | "Medium" | "Low"


    // ── Constructors ────────────────────────────────────────────────────────
}