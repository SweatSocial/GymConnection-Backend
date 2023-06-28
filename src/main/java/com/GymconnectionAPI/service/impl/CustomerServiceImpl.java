package com.GymconnectionAPI.service.impl;

import com.GymconnectionAPI.dto.CustomerDto;
import com.GymconnectionAPI.model.Customer;
import com.GymconnectionAPI.model.Payment;
import com.GymconnectionAPI.repository.CustomerRepository;
import com.GymconnectionAPI.repository.PaymentRepository;
import com.GymconnectionAPI.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    private PaymentRepository paymentRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, PaymentRepository paymentRepository){
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.paymentRepository = paymentRepository;
    }

    public List<CustomerDto> getAllCustomers(){
        return customerRepository.findAll()
                .stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    public CustomerDto createCustomer(CustomerDto customerDto){
        Customer customer = DtoToEntity(customerDto);
        return EntityToDto(customerRepository.save(customer));
    }

    public CustomerDto updateCustomer(Long id, CustomerDto customerDto){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        if (customerDto.getName() != null || customerDto.getLastname() != null || customerDto.getEmail() != null){
            throw new IllegalArgumentException("Name, lastname y email no pueden ser modificados");
        }
        if(customerDto.getAge() !=0 && customerDto.getAge() != customer.getAge()){
            customer.setAge(customerDto.getAge());
        }
        if(customerDto.getPhone() != null && !customerDto.getPhone().equals(customer.getPhone())){
            customer.setPhone(customerDto.getPhone());
        }
        if(customerDto.getPassword() != null && !customerDto.getPassword().equals(customer.getPassword())){
            customer.setPassword(customerDto.getPassword());
        }
        if(customerDto.getDescription() != null && !customerDto.getDescription().equals(customer.getDescription())){
            customer.setDescription(customerDto.getDescription());
        }
        if(customerDto.getDepartment() != null && !customerDto.getDepartment().equals(customer.getDepartment())){
            customer.setDepartment(customerDto.getDepartment());
        }
        if(customerDto.getDistrict() != null && !customerDto.getDistrict().equals(customer.getDistrict())){
            customer.setDistrict(customerDto.getDistrict());
        }
        String profileImageBase64 = customerDto.getProfileImage();
        if (profileImageBase64 != null){
            byte[] profileImageBytes = Base64.getDecoder().decode(profileImageBase64);
        }
        //customer.setProfileImage(customerDto.getProfileImage().getBytes());
        if (customerDto.getPaymentId() != null) {
            // Obtener el objeto Payment correspondiente al nuevo valor
            Payment newPayment = paymentRepository.findById(customerDto.getPaymentId())
                    .orElseThrow(() -> new RuntimeException("Payment not found"));

            // Actualizar el valor del campo payment en el Customer existente
            customer.setPayment(newPayment);
        }
        return EntityToDto(customerRepository.save(customer));
    }


    private CustomerDto EntityToDto(Customer customer){
        return modelMapper.map(customer, CustomerDto.class);
    }

    private Customer DtoToEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto, Customer.class);
    }

}
