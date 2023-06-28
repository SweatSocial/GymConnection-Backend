package com.GymconnectionAPI.service.impl;

import com.GymconnectionAPI.dto.RoutineDto;
import com.GymconnectionAPI.model.Routine;
import com.GymconnectionAPI.repository.RoutineRepository;
import com.GymconnectionAPI.service.RoutineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoutineServiceimpl implements RoutineService {
    private RoutineRepository routineRepository;
    private ModelMapper modelMapper;

    @Autowired
    public RoutineServiceimpl(RoutineRepository routineRepository, ModelMapper modelMapper){
        this.routineRepository=routineRepository;
        this.modelMapper=modelMapper;
    }
    public List<RoutineDto>getAllRoutines(){
        return routineRepository.findAll()
                .stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    public RoutineDto createRoutine(RoutineDto routineDto){
        Routine routine = DtoToEntity(routineDto);
        return EntityToDto(routineRepository.save(routine));
    }
    public RoutineDto EntityToDto(Routine routine){return modelMapper.map(routine, RoutineDto.class);}

    public Routine DtoToEntity(RoutineDto routineDto){return modelMapper.map(routineDto,  Routine.class);}
}
