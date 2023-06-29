package com.GymconnectionAPI.service.impl;

import com.GymconnectionAPI.dto.CoachDto;
import com.GymconnectionAPI.dto.CustomerDto;
import com.GymconnectionAPI.model.Coach;
import com.GymconnectionAPI.repository.CoachRepository;
import com.GymconnectionAPI.repository.PaymentRepository;
import com.GymconnectionAPI.service.CoachService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoachServiceImpl implements CoachService {
    private CoachRepository coachRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CoachServiceImpl(CoachRepository coachRepository, ModelMapper modelMapper){
        this.coachRepository = coachRepository;
        this.modelMapper = modelMapper;
    }

    public List<CoachDto> getAllCoaches(){
        return coachRepository.findAll()
                .stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    public CoachDto createCoach(CoachDto coachDto){
        Coach coach = DtoToEntity(coachDto);
        return EntityToDto(coachRepository.save(coach));
    }


    private CoachDto EntityToDto(Coach coach){
        return modelMapper.map(coach, CoachDto.class);
    }
    private Coach DtoToEntity(CoachDto coachDto){
        return modelMapper.map(coachDto, Coach.class);
    }

}
