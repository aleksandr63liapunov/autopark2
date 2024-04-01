package com.example.autopark.controller;

import com.example.autopark.dto.OwnerDto;
import com.example.autopark.model.Owner;
import com.example.autopark.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @PostMapping("/owner_add")
    public Owner createOwner(@RequestBody Owner owner) {
        return ownerService.createOwner(owner);
    }

    @GetMapping("/{carId}/assign/{ownerId}")
    public void assignCarToOwner(@PathVariable ("carId") Long carId, @PathVariable ("ownerId") Long ownerId) {
        ownerService.assignCarToOwner(carId, ownerId);
    }

    @DeleteMapping("/delete_car_from_owner/{carId}")
    public void removeCarFromOwner(@PathVariable Long carId) {
        ownerService.removeCarFromOwner(carId);
    }

    @GetMapping("/get_all_owners_and_cars")
    public List<OwnerDto> getAllOwnersInfo() {
        return ownerService.getAllOwnersInfo();
    }
}
