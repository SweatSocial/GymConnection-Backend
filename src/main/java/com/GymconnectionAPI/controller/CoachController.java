package com.GymconnectionAPI.controller;

import com.GymconnectionAPI.dto.CoachDto;
import com.GymconnectionAPI.dto.CustomerDto;
import com.GymconnectionAPI.exception.ValidationException;
import com.GymconnectionAPI.repository.CoachRepository;
import com.GymconnectionAPI.service.CoachService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coach/v1")
public class CoachController {
    @Autowired
    private CoachService coachService;
    private final CoachRepository coachRepository;
    private final ModelMapper modelMapper;

    public CoachController(CoachRepository coachRepository, ModelMapper modelMapper) {
        this.coachRepository = coachRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/coaches")
    public List<CoachDto> getAllCoaches() {
        return coachService.getAllCoaches();
    }

    @PostMapping("/coaches")
    public ResponseEntity<CoachDto> createCoach(@RequestBody CoachDto coachDto) {
        existsCoachByEmailAndPhone(coachDto);
        validateCoach(coachDto);
        return new ResponseEntity<>(coachService.createCoach(coachDto), HttpStatus.CREATED);
    }

    private void validateCoach(CoachDto coachDto) {
        if (coachDto == null) {
            throw new ValidationException("CoachDto is null");
        }
        if (coachDto.getName() == null || coachDto.getName().trim().isEmpty()) {
            throw new ValidationException("Name is required");
        }
        if (coachDto.getLastname() == null || coachDto.getLastname().trim().isEmpty()) {
            throw new ValidationException("Lastname is required");
        }
        if (coachDto.getEmail() == null || coachDto.getEmail().trim().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        if (coachDto.getPhone() == null || coachDto.getPhone().trim().isEmpty()) {
            throw new ValidationException("Phone is required");
        }
        if (coachDto.getAge() < 18 || coachDto.getAge() > 80) {
            throw new ValidationException("Age greater than 18 and less than 80 is required");
        }
        if (coachDto.getDepartment() == null || coachDto.getDepartment().trim().isEmpty()) {
            throw new ValidationException("Department is required");
        }
        if(coachDto.getDistrict() == null || coachDto.getDistrict().trim().isEmpty()){
            throw new ValidationException("District is required");
        }
        if((coachDto.getPassword() == null || coachDto.getPassword().trim().isEmpty())){
            throw new ValidationException("Password es requerido");
        }
        if(coachDto.getPassword().length() < 4){
            throw new ValidationException("Password debe tener mínimo 5 caracteres");
        }
    }

    private void existsCoachByEmailAndPhone(CoachDto coachDto){
        if (coachRepository.existsByEmailAndPhone(coachDto.getEmail(), coachDto.getPhone())){
            throw new ValidationException("Ya existe un coach con el mismo email y/o phone");
        }
    }

}


