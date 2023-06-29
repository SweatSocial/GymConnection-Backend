package com.GymconnectionAPI.service;

import com.GymconnectionAPI.dto.GroupDto;

import java.util.List;

public interface GroupService {
    public abstract GroupDto createGroup(GroupDto groupDto);
    public abstract List<GroupDto> getAllGroups();

}
