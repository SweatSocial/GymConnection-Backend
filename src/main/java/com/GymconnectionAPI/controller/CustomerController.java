package com.GymconnectionAPI.controller;

import com.GymconnectionAPI.dto.CustomerDto;
import com.GymconnectionAPI.exception.ValidationException;
import com.GymconnectionAPI.model.Customer;
import com.GymconnectionAPI.repository.CustomerRepository;
import com.GymconnectionAPI.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/v1")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerController(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/customers")
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @Transactional(readOnly = true)
    @GetMapping("/customers/filterByCustomerName")
    public ResponseEntity<List<Customer>> getAllCustomerByCustomerEmail(@RequestParam(name = "email") String email) {
        return new ResponseEntity<List<Customer>>(customerRepository.findByEmail(email), HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        existsCustomerByEmailAndPhone(customerDto);
        validateCustomer(customerDto);
        return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @PatchMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        existsCustomerByEmailAndPhone(customerDto);
        return new ResponseEntity<>(customerService.updateCustomer(id, customerDto), HttpStatus.OK);
    }

    private void validateCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new ValidationException("Request body is missing");
        }
        if (customerDto.getEmail() == null || customerDto.getEmail().trim().isEmpty()) {
            throw new ValidationException("Email es requerido");
        }
        if (customerDto.getPhone() == null || customerDto.getPhone().trim().isEmpty()) {
            throw new ValidationException("Phone es requerido");
        }
        if (customerDto.getName() == null || customerDto.getName().trim().isEmpty()) {
            throw new ValidationException("Name es requerido");
        }
        if (customerDto.getLastname() == null || customerDto.getLastname().trim().isEmpty()) {
            throw new ValidationException("Lastname es requerido");
        }
        if (customerDto.getAge() < 18 || customerDto.getAge() > 80){
            throw new ValidationException("Age debe ser menor que 18 y mayor que 80");
        }
        if (customerDto.getDescription() == null || customerDto.getDescription().trim().isEmpty()) {
            throw new ValidationException("Description es requerido");
        }
        if (customerDto.getDepartment() == null || customerDto.getDepartment().trim().isEmpty()){
            throw new ValidationException("Department es requerido");
        }
        if (customerDto.getDistrict() == null || customerDto.getDistrict().trim().isEmpty()) {
            throw new ValidationException("District es requerido");
        }
        if((customerDto.getPassword() == null || customerDto.getPassword().trim().isEmpty())){
            throw new ValidationException("Password es requerido");
        }
        if(customerDto.getPassword().length() < 4){
            throw new ValidationException("Password debe tener mínimo 5 caracteres");
        }
    }

private void existsCustomerByEmailAndPhone(CustomerDto customerDto){
        if (customerRepository.existsByEmailAndPhone(customerDto.getEmail(), customerDto.getPhone())){
            throw new ValidationException("Ya existe un customer con el mismo email y/o phone");
        }
}

}
