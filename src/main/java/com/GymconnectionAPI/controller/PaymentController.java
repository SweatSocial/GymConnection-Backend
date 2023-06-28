package com.GymconnectionAPI.controller;


import com.GymconnectionAPI.dto.CustomerDto;
import com.GymconnectionAPI.dto.PaymentDto;
import com.GymconnectionAPI.exception.ValidationException;
import com.GymconnectionAPI.model.Payment;
import com.GymconnectionAPI.repository.CustomerRepository;
import com.GymconnectionAPI.repository.PaymentRepository;
import com.GymconnectionAPI.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @Transactional(readOnly = true)
    @GetMapping("/payments/filterById")
    public ResponseEntity<List<Payment>> getAllPaymentById(@RequestParam(name = "id") Long id) {
        return new ResponseEntity (paymentRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/payments")
    public List<PaymentDto> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @PostMapping("/payments")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        validatePayment(paymentDto);
        return new ResponseEntity<>(paymentService.createPayment(paymentDto), HttpStatus.CREATED);
    }

    private void validatePayment(PaymentDto paymentDto) {
        if(paymentDto.getOption() == null) {
            throw new ValidationException("Request option is missing");
        }
        if(paymentDto.getName() == null) {
            throw new ValidationException("Request name is missing");
        }
        if(paymentDto.getDue_date() == null) {
            throw new ValidationException("Request due_date is missing");
        }
        if(paymentDto.getSecurity_code() < 100 && paymentDto.getSecurity_code() > 999) {
            throw new ValidationException("Request security_code is missing");
        }
    }

    private void existsPaymentById(PaymentDto paymentDto){
        if (!(paymentRepository.existsById(paymentDto.getId()))) {
            throw new ValidationException("Payment with id: " + paymentDto.getId() + " don't exists");
        }
    }

}









