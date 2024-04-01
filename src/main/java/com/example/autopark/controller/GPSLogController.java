package com.example.autopark.controller;

import com.example.autopark.service.GPSLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gps")
public class GPSLogController {
    @Autowired
    private GPSLogService gpsLogService;

    @GetMapping("/calculate_distance")
    public ResponseEntity<Double> calculateDistanceTraveled(@RequestBody String gpsData) {
        double distance = gpsLogService.calculateDistanceTraveled(gpsData);
        return ResponseEntity.ok(distance);
    }
}
