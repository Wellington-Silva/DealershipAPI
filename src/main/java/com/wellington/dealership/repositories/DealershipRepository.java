package com.wellington.dealership.repositories;

import com.wellington.dealership.domains.Dealership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DealershipRepository extends JpaRepository<Dealership, UUID> {
    Optional<Dealership> findByEmail(String email);
}
