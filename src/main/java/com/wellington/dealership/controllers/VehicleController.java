package com.wellington.dealership.controllers;

import com.wellington.dealership.DTOs.CreateVehicleDTO;
import com.wellington.dealership.domains.Vehicle;
import com.wellington.dealership.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<Vehicle>> listVehicles(
            @RequestParam UUID dealershipId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Vehicle> vehicles = service.listVehiclesByDealership(dealershipId, page, size);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{plate}")
    public ResponseEntity vehicleDetails(@PathVariable String plate) {
        return ResponseEntity.ok(this.service.vehicleDetails(plate));
    }

    @PostMapping
    public ResponseEntity<?> createVehicle(@RequestBody @Valid CreateVehicleDTO data) {
        try {
            Vehicle vehicle = this.service.createVehicle(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}