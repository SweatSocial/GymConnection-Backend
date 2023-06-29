package com.GymconnectionAPI.controller;


import com.GymconnectionAPI.dto.GroupDto;
import com.GymconnectionAPI.exception.ValidationException;
import com.GymconnectionAPI.repository.GroupRepository;
import com.GymconnectionAPI.service.GroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group/v1")
public class GroupController {

    @Autowired
    private GroupService groupService;
    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;
    public GroupController(GroupRepository groupRepository, ModelMapper modelMapper) {
        this.groupRepository = groupRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/groups")
    public List<GroupDto> getAllGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping("/groups")
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
        existsGroupByCustomerAndCoach(groupDto);
        validateGroup(groupDto);
        return new ResponseEntity<>(groupService.createGroup(groupDto), HttpStatus.CREATED);
    }

    private void validateGroup(GroupDto groupDto) {
        if (groupDto == null) {
            throw new ValidationException("Request body is missing");
        }
        if (groupDto.getCustomersIds() == null) {
            throw new ValidationException("Customer es requerido");
        }
        if (groupDto.getGroupLink() == null || groupDto.getGroupLink().trim().isEmpty()) {
            throw new ValidationException("GroupLink es requerido");
        }
    }

    private void existsGroupByCustomerAndCoach(GroupDto groupDto) {
        if (groupRepository.existsByGroupLink(groupDto.getGroupLink())) {
            throw new ValidationException("Group link already exists");
        }
    }
}
