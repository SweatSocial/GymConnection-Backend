package com.GymconnectionAPI.service;
import com.GymconnectionAPI.dto.CustomerDto;
import  com.GymconnectionAPI.dto.PaymentDto;

import java.util.List;


public interface PaymentService {
    public abstract PaymentDto createPayment(PaymentDto paymentDto );
    public abstract List<PaymentDto> getAllPayments();
    public abstract CustomerDto updateCustomer(Long id, CustomerDto customerDto);
}
