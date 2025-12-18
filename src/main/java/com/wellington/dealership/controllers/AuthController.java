package com.wellington.dealership.controllers;

import com.wellington.dealership.DTOs.LoginRequestDTO;
import com.wellington.dealership.DTOs.RegisterRequestDTO;
import com.wellington.dealership.DTOs.ResponseDTO;
import com.wellington.dealership.domains.Dealership;
import com.wellington.dealership.infra.security.TokenService;
import com.wellington.dealership.repositories.DealershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final DealershipRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        Dealership dealership = repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.password(), dealership.getPassword())) {
            String token = this.tokenService.generateToken(dealership);
            return ResponseEntity.ok(new ResponseDTO(dealership.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        Optional<Dealership> dealership = repository.findByEmail(body.email());

        if (dealership.isEmpty()) {
            Dealership newDealership = new Dealership();

            newDealership.setPassword(passwordEncoder.encode(body.password()));
            newDealership.setEmail(body.email());
            newDealership.setShortAddress(body.shortAddress());
            newDealership.setName(body.name());
            this.repository.save(newDealership);

            String token = this.tokenService.generateToken(newDealership);
            return ResponseEntity.ok(new ResponseDTO(newDealership.getName(), token));
        }

        return ResponseEntity.badRequest().build();
    }
}
