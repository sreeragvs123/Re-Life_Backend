package com.example.Relife_backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    // Stored filename on disk (e.g. "1714900000000_disaster_training.mp4")
    // The full URL is assembled in the service from this + base server URL
    @Column(nullable = false)
    private String fileName;

    // 'pending' or 'approved'
    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String uploader;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) this.status = "pending";
    }
}