package com.example.autopark.service;

import com.example.autopark.dto.CarDto;
import com.example.autopark.dto.DealerDto;
import com.example.autopark.dto.OwnerDto;
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
public class OwnerService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private DealerRepository dealerRepository;

    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public void deleteOwner(Long ownerId) {
        ownerRepository.deleteById(ownerId);
    }

    public void assignCarToOwner(Long carId, Long ownerId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("Owner not found"));

        car.setOwner(owner);
        carRepository.save(car);
    }

    public void removeCarFromOwner(Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));

        car.setOwner(null);
        carRepository.save(car);
    }

    public List<OwnerDto> getAllOwnersInfo() {
        List<OwnerDto> ownerDtos = new ArrayList<>();
        List<Owner> owners = ownerRepository.findAll();
        for (Owner owner : owners) {
            OwnerDto ownerDto = new OwnerDto();
            ownerDto.setId(owner.getId());
            ownerDto.setFullName(owner.getFullName());
            ownerDto.setOwnedCars(getOwnedCars(owner));
            ownerDtos.add(ownerDto);
        }
        return ownerDtos;
    }

    private List<CarDto> getOwnedCars(Owner owner) {
        List<CarDto> ownedCars = new ArrayList<>();
        List<Car> cars = owner.getCars();
        for (Car car : cars) {
            CarDto carDTO = new CarDto();
            carDTO.setId(car.getId());
            carDTO.setUniqueNumber(car.getUniqueNumber());
            carDTO.setManufactureDate(car.getProductionDate());
            ownedCars.add(carDTO);
        }
        return ownedCars;
    }
}
