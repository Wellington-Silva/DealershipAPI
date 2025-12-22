package com.wellington.dealership.controllers;

import com.wellington.dealership.DTOs.CreateVehicleDTO;
import com.wellington.dealership.DTOs.UpdateVehicleDTO;
import com.wellington.dealership.domains.Vehicle;
import com.wellington.dealership.services.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @Operation(description = "Lista os veículos cadastrados na concessionária")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de veículos"),
            @ApiResponse(responseCode = "404", description = "Nenhum veículo cadastrado")
    })
    @GetMapping
    public ResponseEntity<Page<Vehicle>> listVehicles(
            @RequestParam UUID dealershipId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Vehicle> vehicles = service.listVehiclesByDealership(dealershipId, page, size);
        return ResponseEntity.ok(vehicles);
    }

    @Operation(description = "Veículo encontrado pela placa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna um veículo"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    @GetMapping("/{plate}")
    public ResponseEntity vehicleDetails(@PathVariable String plate) {
        return ResponseEntity.ok(this.service.vehicleDetails(plate));
    }

    @Operation(description = "Cadastra um veículo associado a concessionária")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo cadastrado"),
            @ApiResponse(responseCode = "400", description = "Veículo não cadastrado")
    })
    @PostMapping
    public ResponseEntity<?> createVehicle(@RequestBody @Valid CreateVehicleDTO data) {
        try {
            Vehicle vehicle = this.service.createVehicle(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @Operation(description = "Atualiza dados de um veículo associado a concessionária")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo atualizado"),
            @ApiResponse(responseCode = "400", description = "Veículo não atualizado")
    })
    @PutMapping("/update/vehicle/{plate}")
    public ResponseEntity<?> updateVehicle(@PathVariable String plate, @RequestBody @Valid UpdateVehicleDTO data) {
        try {
            Optional<Vehicle> vehicle = this.service.updateVehicle(plate, data);
            return ResponseEntity.status(HttpStatus.OK).body(vehicle);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @Operation(description = "Deleta um veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleta um veículo"),
            @ApiResponse(responseCode = "400", description = "Erro ao deletar um veículo")
    })
    @DeleteMapping("/{plate}")
    public ResponseEntity deleteVehicle(@PathVariable String plate) {
        service.deleteVehicle(plate);
        return ResponseEntity.ok("Veículo deletado com sucesso");
    }

}