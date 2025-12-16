package com.wellington.dealership.domains;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name="dealership")
@Entity(name="dealership")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dealership {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String shortAddress;

    private String email;

    private String password;

}
