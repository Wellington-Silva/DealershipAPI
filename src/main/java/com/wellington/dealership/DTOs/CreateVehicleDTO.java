package com.wellington.dealership.DTOs;

import java.util.UUID;

public record CreateVehicleDTO(
        String name,
        String model,
        String plate,
        Integer year,
        UUID dealershipId
) {}
