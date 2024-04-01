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
public class DealerService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private DealerRepository dealerRepository;

    public Dealer createDealer(Dealer dealer) {
        return dealerRepository.save(dealer);
    }

    public void deleteDealer(Long dealerId) {
        dealerRepository.deleteById(dealerId);
    }

    public void assignOwnerToDealer(Long ownerId, Long dealerId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("Owner not found"));
        Dealer dealer = dealerRepository.findById(dealerId).orElseThrow(() -> new RuntimeException("Dealer not found"));

        owner.setDealer(dealer);
        ownerRepository.save(owner);
    }

    public void removeOwnerFromDealer(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("Owner not found"));

        owner.setDealer(null);
        ownerRepository.save(owner);
    }

    public List<DealerDto> getAllDealersInfo() {
        List<Dealer> dealers = dealerRepository.findAll();
        List<DealerDto> dealerDTOs = new ArrayList<>();
        for (Dealer dealer : dealers) {
            DealerDto dealerDTO = new DealerDto();
            dealerDTO.setId(dealer.getId());
            dealerDTO.setName(dealer.getName());
            dealerDTO.setOwnerDtoList(getOwnerDealers(dealer));

            dealerDTOs.add(dealerDTO);
        }
        return dealerDTOs;
    }

    private List<OwnerDto> getOwnerDealers(Dealer dealer) {
        List<OwnerDto> ownerDtos = new ArrayList<>();
        List<Owner> owners = dealer.getOwners();
        for (Owner owner : owners) {
            OwnerDto ownerDto = new OwnerDto();
            ownerDto.setId(owner.getId());
            ownerDto.setFullName(owner.getFullName());
            ownerDto.setOwnedCars(getOwnedCars(dealer));

            ownerDtos.add(ownerDto);
        }
        return ownerDtos;
    }

    private List<CarDto> getOwnedCars(Dealer dealer) {
        List<CarDto> ownedCars = new ArrayList<>();
        List<Owner> owners = dealer.getOwners();
        for (Owner owner : owners) {
            for (Car car : owner.getCars()) {
                CarDto carDTO = new CarDto();
                carDTO.setId(car.getId());
                carDTO.setUniqueNumber(car.getUniqueNumber());
                carDTO.setManufactureDate(car.getProductionDate());
                ownedCars.add(carDTO);
            }
        }
        return ownedCars;
    }
}
