package com.example.autopark.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CarDto {
    private Long id;
    private String uniqueNumber;
    private Date manufactureDate;
}
