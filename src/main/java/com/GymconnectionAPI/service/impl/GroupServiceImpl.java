package com.GymconnectionAPI.service.impl;

import com.GymconnectionAPI.dto.GroupDto;
import com.GymconnectionAPI.model.Coach;
import com.GymconnectionAPI.model.Customer;
import com.GymconnectionAPI.model.Group;
import com.GymconnectionAPI.repository.CoachRepository;
import com.GymconnectionAPI.repository.CustomerRepository;
import com.GymconnectionAPI.repository.GroupRepository;
import com.GymconnectionAPI.service.GroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;
    private ModelMapper modelMapper;
    private CustomerRepository customerRepository;
    private CoachRepository coachRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, CustomerRepository customerRepository, ModelMapper modelMapper, CoachRepository coachRepository){
        this.groupRepository = groupRepository;
        this.modelMapper = modelMapper;
        this.coachRepository = coachRepository;
        this.customerRepository = customerRepository;
    }

    public List<GroupDto> getAllGroups(){
        return groupRepository.findAll()
                .stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    public GroupDto createGroup(GroupDto groupDto){
        Group group = DtoToEntity(groupDto);
        if(groupDto.getCoachId() != null){
            Coach coach = coachRepository.findById(groupDto.getCoachId())
                    .orElseThrow(() -> new RuntimeException("Coach not found"));
            group.setCoach(coach);
        }
        if(groupDto.getCustomersIds() != null){
            List<Customer> customers = customerRepository.findAllById(groupDto.getCustomersIds());
            group.setCustomers(customers);
        }
        return EntityToDto(groupRepository.save(group));
    }

    public GroupDto updateGroup(Long id, GroupDto groupDto){
        Group group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Group not found"));
        if (groupDto.getGroupLink() != null && !groupDto.getGroupLink().equals(group.getGroupLink())){
            group.setGroupLink(groupDto.getGroupLink());
        }
        if(groupDto.getCoachId() !=null){
            Coach newCoach = coachRepository.findById(groupDto.getCoachId())
                    .orElseThrow(() -> new RuntimeException("Coach not found"));
            group.setCoach(newCoach);
        }
        /*
        if(groupDto.getCustomersIds() != null){
            for (Long customerId : groupDto.getCustomersIds()){

            }
            CustomerRepository newCustomer = customerRepository.findById(groupDto.getCustomersIds())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
        }
        */

        return EntityToDto(groupRepository.save(group));
    }

    private GroupDto EntityToDto(Group group){
        return modelMapper.map(group, GroupDto.class);
    }

    private Group DtoToEntity(GroupDto groupDto){
        return modelMapper.map(groupDto, Group.class);
    }
}
