package com.GymconnectionAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
