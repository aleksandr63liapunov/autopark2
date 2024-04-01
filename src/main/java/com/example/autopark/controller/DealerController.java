package com.example.autopark.controller;

import com.example.autopark.dto.DealerDto;
import com.example.autopark.model.Dealer;
import com.example.autopark.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dealers")
public class DealerController {
    @Autowired
    private DealerService dealerService;

    @GetMapping("/dealer_add")
    public Dealer createDealer(Dealer dealer) {
        return dealerService.createDealer(dealer);
    }

    @GetMapping("/{ownerId}/assign/{dealerId}")
    public void assignOwnerToDealer(@PathVariable("ownerId") Long ownerId, @PathVariable("dealerId") Long dealerId) {
        dealerService.assignOwnerToDealer(ownerId,dealerId);
    }

    @GetMapping("/delete_owner_from_dealer/{ownerId}")
    public void removeOwnerFromDealer(@PathVariable ("ownerId") Long ownerId) {
        dealerService.removeOwnerFromDealer(ownerId);
    }
    @GetMapping("/get_info_dealers")
    public List<DealerDto> getAllDealersInfo() {
        return dealerService.getAllDealersInfo();
    }
}
