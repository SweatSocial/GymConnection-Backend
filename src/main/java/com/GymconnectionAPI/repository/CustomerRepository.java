package com.GymconnectionAPI.repository;

import com.GymconnectionAPI.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByEmail(String email);

    boolean existsByEmailAndPhone(String email, String phone);

}
