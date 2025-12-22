package com.wellington.dealership.services;

import com.wellington.dealership.DTOs.UpdateRequestDTO;
import com.wellington.dealership.domains.Dealership;
import com.wellington.dealership.repositories.DealershipRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@Service
public class DealershipService {
    
    @Autowired
    private DealershipRepository repository;

    public Optional<Dealership> dealershipDetails(UUID id) {
        return Optional.of(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dealership not found")));
    }

    public Dealership updateDealership(UUID id, UpdateRequestDTO data) {
        return repository.findById(id)
                .map(dealership -> {
                    dealership.setName(data.name());
                    dealership.setShortAddress(data.shortAddress());
                    return repository.save(dealership);
                })
                .orElseThrow(() -> new EntityNotFoundException("Concessionária não encontrada"));
    }

    public ResponseEntity<Void> deleteDealership(@PathVariable UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
