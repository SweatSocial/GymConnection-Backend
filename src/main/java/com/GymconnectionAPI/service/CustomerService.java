package com.GymconnectionAPI.service;

import com.GymconnectionAPI.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    public abstract CustomerDto createCustomer(CustomerDto customerDto);
    public abstract List<CustomerDto> getAllCustomers();
    public abstract CustomerDto updateCustomer(Long id, CustomerDto customerDto);
}
