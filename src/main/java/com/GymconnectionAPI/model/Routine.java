package com.GymconnectionAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "routines")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Columnas obligatorias al momento de registrarse
    @Column(name = "name",length =100,nullable = false)
    private String name;
    @Column(name = "ejercicio",length =100,nullable = false)
    private String ejercicio;
    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false,
    foreignKey = @ForeignKey(name = "FK_routines_customers"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Customer customer;
}
