package com.wellington.dealership.DTOs;

public record UpdateRequestDTO(
        String name,
        String email,
        String shortAddress) {}
