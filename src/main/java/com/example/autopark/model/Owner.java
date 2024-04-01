package com.example.autopark.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Car> cars = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;
}
