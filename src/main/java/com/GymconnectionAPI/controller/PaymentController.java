package com.GymconnectionAPI.controller;


import com.GymconnectionAPI.dto.CustomerDto;
import com.GymconnectionAPI.dto.PaymentDto;
import com.GymconnectionAPI.repository.CustomerRepository;
import com.GymconnectionAPI.repository.PaymentRepository;
import com.GymconnectionAPI.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payment/v1")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    public PaymentController(PaymentRepository paymentRepository, ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/payments")
    public List<PaymentDto> getAllPayments() {
        return paymentService.getAllPayments();
    }
}
