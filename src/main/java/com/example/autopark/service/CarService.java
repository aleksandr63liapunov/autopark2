package com.example.autopark.service;

import com.example.autopark.model.Car;
import com.example.autopark.model.Dealer;
import com.example.autopark.model.Owner;
import com.example.autopark.repo.CarRepository;
import com.example.autopark.repo.DealerRepository;
import com.example.autopark.repo.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private DealerRepository dealerRepository;

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }






}
