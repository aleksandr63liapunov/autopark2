package com.example.autopark.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uniqueNumber;
    private Date productionDate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

}
