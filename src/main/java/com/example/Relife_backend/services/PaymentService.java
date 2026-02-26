package com.example.Relife_backend.services;


import com.example.Relife_backend.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {

    PaymentDTO createPayment(PaymentDTO dto);

    List<PaymentDTO> getAllPayments();
}