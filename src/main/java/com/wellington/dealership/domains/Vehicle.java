package com.wellington.dealership.domains;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name="vehicle")
@Entity(name="vehicle")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String model;

    private String plate;

    private Integer year;

    @Column(name = "dealership_id", nullable = false)
    private UUID dealershipId;
}
