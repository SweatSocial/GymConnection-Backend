package com.GymconnectionAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CoachDto extends UserDto {
    private String name;
    private String lastname;
    private int age;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String profileImage;
    private String department;
    private String district;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String phone;
}
