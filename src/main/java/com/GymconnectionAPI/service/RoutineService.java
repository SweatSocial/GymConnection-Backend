package com.GymconnectionAPI.service;

import com.GymconnectionAPI.dto.RoutineDto;
import com.GymconnectionAPI.model.Routine;

import java.util.List;

public interface RoutineService {
    public abstract RoutineDto createRoutine(RoutineDto routineDto);
    public abstract List<RoutineDto> getAllRoutines();
}
