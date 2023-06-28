package com.GymconnectionAPI.dto;

import com.GymconnectionAPI.model.Coach;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {
    private Long id;
    private Long coachId;
    private String groupLink;
    private List<Long> customersIds;
}
