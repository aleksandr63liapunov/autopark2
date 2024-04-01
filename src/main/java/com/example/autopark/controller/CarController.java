package com.example.autopark.controller;

import com.example.autopark.model.Car;
import com.example.autopark.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/car_add")
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

    @DeleteMapping("/delete_car/{carId}")
    public void deleteCar(@PathVariable ("carId")Long carId) {
        carService.deleteCar(carId);
    }


}
