package com.GymconnectionAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutineDto {
    private Long id;
    private String name;
    private String ejercicio;
    private Long customerId;
}
