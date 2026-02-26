package com.example.Relife_backend.entities;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Razorpay's payment ID returned on success (e.g. "pay_QxYz123")
    @Column(nullable = false)
    private String razorpayPaymentId;

    @Column(nullable = false)
    private Double amount;

    private String contact;

    private String email;

    // Always "SUCCESS" for now — extend later if you add refunds/failures
    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}