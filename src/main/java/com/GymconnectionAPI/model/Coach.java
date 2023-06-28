package com.GymconnectionAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coachs")
public class Coach extends User {

    //Columnas obligatorias al momento de registrarse
    @Column(name = "name",length =100,nullable = false)
    private String name;
    @Column(name = "lastname",length =100,nullable = false)
    private String lastname;
    @Column(name = "age")
    private int age;
    @Lob
    @Column(name = "profile_image")
    private byte[] profileImage;
    @Column(name = "department",length =50,nullable = false)
    private String department;
    @Column(name = "district",length =50,nullable = false)
    private String district;
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    @Column(name = "password",length =100,nullable = false)
    private String password;
    @Column(name = "phone",length =100,nullable = false)
    private String phone;
}
