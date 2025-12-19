package com.wellington.dealership.controllers;

import com.wellington.dealership.DTOs.UpdateRequestDTO;
import com.wellington.dealership.domains.Dealership;
import com.wellington.dealership.services.DealershipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/dealership")
public class DealershipController {

    @Autowired
    DealershipService service;

    @Operation(description = "Concessionária encontrada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a concessionária"),
            @ApiResponse(responseCode = "404", description = "Concessionária não cadastrada")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity dealershipDetails(@PathVariable UUID id) {
        Optional<Dealership> dealership = service.dealershipDetails(id);
        return ResponseEntity.ok(dealership);
    }

    @Operation(description = "Edita os dados da concessionária")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edita dados da concessionária"),
            @ApiResponse(responseCode = "400", description = "Concessionária não editada")
    })
    @PutMapping("/update/dealership/{id}")
    public ResponseEntity<Dealership> updateDealership(
            @PathVariable UUID id,
            @RequestBody UpdateRequestDTO data) {
        try {
            Dealership updated = service.updateDealership(id, data);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            System.out.println("ERRO NO SERVICE: " + e.getMessage());
            throw e;
        }
    }

    @Operation(description = "Deleta uma concessionária")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleta a concessionária"),
            @ApiResponse(responseCode = "400", description = "Erro ao deletar uma concessionária")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDealership(@PathVariable UUID id) {
        ResponseEntity<Void> deleted = service.deleteDealership(id);
        return ResponseEntity.ok("Sua conta foi deletada com sucesso");
    }

}
