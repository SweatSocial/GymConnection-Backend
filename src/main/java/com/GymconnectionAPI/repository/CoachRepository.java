package com.GymconnectionAPI.repository;

import com.GymconnectionAPI.model.Coach;
import com.GymconnectionAPI.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    List<Coach> findByEmail(String email);

    boolean existsByEmailAndPhone(String email, String phone);
}
