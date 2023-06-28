package com.GymconnectionAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Columnas obligatorias al momento de registrarse
    @Column(name = "payment_option",nullable = false)
    private String option;
    @Column(name = "name",length =100,nullable = false)
    private String name;
    @Column(name = "due_date",nullable = false)
    private LocalDate due_date;
    @Column(name = "security_code",nullable = false)
    private int security_code;
}
