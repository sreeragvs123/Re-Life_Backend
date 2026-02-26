package com.example.Relife_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Long id;                    // null on create

    private String razorpayPaymentId;

    private Double amount;

    private String contact;

    private String email;

    private String status;

    private LocalDateTime createdAt;   // null on create, set by backend
}