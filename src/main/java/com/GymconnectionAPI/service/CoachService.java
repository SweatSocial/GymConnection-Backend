package com.GymconnectionAPI.service;

import com.GymconnectionAPI.dto.CoachDto;

import java.util.List;

public interface CoachService {
    public abstract CoachDto createCoach(CoachDto coachDto);
    public abstract List<CoachDto> getAllCoaches();
}
