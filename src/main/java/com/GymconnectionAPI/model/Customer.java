package com.GymconnectionAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "customers")
public class Customer extends User {

    //Columnas obligatorias al momento de registrarse
    @Column(name = "name",length =100,nullable = false)
    private String name;
    @Column(name = "lastname",length =100,nullable = false)
    private String lastname;
    @Column(name = "age",nullable = false)
    private int age;
    @Column(name = "description",length =250,nullable = false)
    private String description;
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
    @ManyToOne
    @JoinColumn(name = "payment_id", foreignKey = @ForeignKey(name = "FK_customers_payment"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Payment payment;
}
