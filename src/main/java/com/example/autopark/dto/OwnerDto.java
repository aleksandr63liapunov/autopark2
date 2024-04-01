package com.example.autopark.dto;

import lombok.Data;

import java.util.List;

@Data
public class OwnerDto {
    private Long id;
    private String fullName;
//    private String phone;
//    private String email;
    private List<CarDto> ownedCars;
}
