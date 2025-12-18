package com.wellington.dealership.controllers;

import com.wellington.dealership.DTOs.UpdateRequestDTO;
import com.wellington.dealership.domains.Dealership;
import com.wellington.dealership.services.DealershipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping
public class DealershipController {

    @Autowired
    DealershipService service;

    @GetMapping("/{id}")
    public ResponseEntity dealershipDetails(@PathVariable UUID id) {
        Optional<Dealership> dealership = service.dealershipDetails(id);
        return ResponseEntity.ok(dealership);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dealership> updateDealership(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateRequestDTO data) {
        Dealership updated = service.updateDealership(id, data);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping
    public ResponseEntity deleteDealership(@PathVariable UUID id) {
        ResponseEntity<Void> deleted = service.deleteDealership(id);
        return ResponseEntity.ok("Sua conta foi deletada com sucesso");
    }

}
