package com.wellington.dealership.services;

import com.wellington.dealership.DTOs.CreateVehicleDTO;
import com.wellington.dealership.domains.Vehicle;
import com.wellington.dealership.repositories.DealershipRepository;
import com.wellington.dealership.repositories.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    @Autowired
    private DealershipRepository dealershipRepository;

    public Page<Vehicle> listVehiclesByDealership(UUID dealershipId, int page, int size) {
        dealershipRepository.findById(dealershipId)
                .orElseThrow(() -> new IllegalArgumentException("Concessionária não encontrada"));

        return repository.findByDealershipId(dealershipId,
                PageRequest.of(page, size));
    }

    public Optional<Vehicle> vehicleDetails (String plate) {
        return repository.findByPlate(plate);
    }

    public Vehicle createVehicle(CreateVehicleDTO data) {
        // Verifica se placa já existe NA MESMA CONCESSIONÁRIA
        Optional<Vehicle> existing = repository.findByPlateAndDealershipId(
                data.plate(),
                data.dealershipId()
        );

        if (existing.isPresent()) {
            throw new IllegalArgumentException(
                    "Veículo com placa " + data.plate() + " já cadastrado na concessionária"
            );
        }

        // Verifica se concessionária existe
        dealershipRepository.findById(data.dealershipId())
                .orElseThrow(() -> new IllegalArgumentException("Concessionária não encontrada"));

        Vehicle newVehicle = new Vehicle();
        newVehicle.setName(data.name());
        newVehicle.setModel(data.model());
        newVehicle.setPlate(data.plate());
        newVehicle.setYear(data.year());
        newVehicle.setDealershipId(data.dealershipId());  // ← Associa à concessionária

        return repository.save(newVehicle);
    }

}
