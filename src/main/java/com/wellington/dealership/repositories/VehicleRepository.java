package com.wellington.dealership.repositories;

import com.wellington.dealership.domains.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    Optional<Vehicle> findByPlate(String plate);
    Optional<Vehicle> findByPlateAndDealershipId(String plate, UUID dealershipId);
    Page<Vehicle> findByDealershipId(UUID dealershipId, Pageable pageable);
}
