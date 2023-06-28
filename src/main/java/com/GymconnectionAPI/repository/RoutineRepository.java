package com.GymconnectionAPI.repository;

import com.GymconnectionAPI.model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
    List<Routine> findAllById(long id);
}
