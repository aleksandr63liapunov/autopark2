package com.example.autopark.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Dealer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String representativeFullName;

    @OneToMany(mappedBy = "dealer")
    List<Owner> owners =new ArrayList<>();
}
