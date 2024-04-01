package com.example.autopark.dto;

import lombok.Data;

import java.util.List;

@Data
public class DealerDto {
    private Long id;
    private String name;
//    private String email;
//    private String representativeFullName;
//    private List<CarDto> ownedCars;
    private List<OwnerDto> ownerDtoList;

}
