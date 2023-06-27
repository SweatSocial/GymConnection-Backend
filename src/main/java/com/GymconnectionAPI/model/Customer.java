package com.GymconnectionAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //Columnas obligatorias al momento de registrarse
    @Column(name = "name",length =100,nullable = false)
    private String name;
    @Column(name = "lastname",length =100,nullable = false)
    private String lastname;
    @Column(name = "address",length =100,nullable = false)
    private String address;
    @Column(name = "district",length =50,nullable = false)
    private String district;
    @Column(name = "password",length =100,nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "payment_id",nullable = false,
            foreignKey = @ForeignKey(name = "FK_customers_payment"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Payment payment;
    //Informacion que se puede llenar despues
    private int department;
    private int smartphone;

}
