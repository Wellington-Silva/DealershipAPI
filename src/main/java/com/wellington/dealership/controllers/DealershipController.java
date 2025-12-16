package com.wellington.dealership.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DealershipController {

    @GetMapping
    public ResponseEntity details() {
        return ResponseEntity.ok("OK");
    }

    @PostMapping
    public ResponseEntity create() {
        return ResponseEntity.ok().build();
    }

}
