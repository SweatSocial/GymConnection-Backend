package com.GymconnectionAPI.service.impl;


import com.GymconnectionAPI.dto.PaymentDto;
import com.GymconnectionAPI.model.Payment;
import com.GymconnectionAPI.repository.PaymentRepository;
import com.GymconnectionAPI.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceimpl implements PaymentService {
    private PaymentRepository paymentRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PaymentServiceimpl(PaymentRepository paymentRepository, ModelMapper modelMapper){
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }

    public List<PaymentDto> getAllPayments(){
        return paymentRepository.findAll()
                .stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    public PaymentDto createPayment(PaymentDto paymentDto){
        Payment payment = DtoToEntity(paymentDto);
        return EntityToDto(paymentRepository.save(payment));
    }

    public PaymentDto updatePayment(Long id, PaymentDto paymentDto){
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));

        if(paymentDto.getOption() !=null && !paymentDto.getOption().equals(payment.getOption())){
            payment.setOption(paymentDto.getOption());
        }
        if (paymentDto.getName() != null){
            throw new IllegalArgumentException("Name no puede ser modificado");
        }
        if(paymentDto.getDue_date() !=null && !paymentDto.getDue_date().equals(payment.getDue_date())){
            payment.setDue_date(paymentDto.getDue_date());
        }
        if(paymentDto.getSecurity_code() !=0 && paymentDto.getSecurity_code() != payment.getSecurity_code()){
            payment.setSecurity_code(paymentDto.getSecurity_code());
        }
        return EntityToDto(paymentRepository.save(payment));
    }

    private PaymentDto EntityToDto(Payment payment){
        return modelMapper.map(payment, PaymentDto.class);
    }
    private Payment DtoToEntity(PaymentDto paymentDto){
        return modelMapper.map(paymentDto, Payment.class);
    }
}
