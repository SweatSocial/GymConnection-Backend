package com.GymconnectionAPI.repository;

import com.GymconnectionAPI.model.Coach;
import com.GymconnectionAPI.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findById(long id);
}
