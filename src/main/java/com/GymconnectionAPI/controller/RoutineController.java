package com.GymconnectionAPI.controller;

import com.GymconnectionAPI.dto.RoutineDto;
import com.GymconnectionAPI.repository.RoutineRepository;
import com.GymconnectionAPI.service.RoutineService;
import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routines/v1")
public class RoutineController {
    @Autowired
    private RoutineService routineService;
    private final RoutineRepository routineRepository;
    private final ModelMapper modelMapper;

    public RoutineController(RoutineRepository routineRepository,ModelMapper modelMapper){
        this.routineRepository=routineRepository;
        this.modelMapper=modelMapper;
    }


    @GetMapping("/routines")
    public List<RoutineDto> getAllRoutines(){
        return routineService.getAllRoutines();
    }

    @PostMapping("/newroutine")
    public ResponseEntity<RoutineDto> createRoutine(@RequestBody RoutineDto routineDto){
        validateRoutine(routineDto);
        return new ResponseEntity<>(routineService.createRoutine(routineDto), HttpStatus.CREATED);
    }

    private  void validateRoutine(RoutineDto routineDto){
        if (routineDto.getName()==null){
            throw new com.GymconnectionAPI.exception.ValidationException("Request name is missing");
        }
        if (routineDto.getEjercicio()==null){
            throw new com.GymconnectionAPI.exception.ValidationException("Request ejercicio is missing");
        }
        if (routineDto.getCustomerId()==null){
            throw new com.GymconnectionAPI.exception.ValidationException("Request custome id is missing");
        }
    }
}
