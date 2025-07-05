package com.example.kaamelott.features.quetes.dtos.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Data Transfer Object for representing a Quête in the Kaamelott application.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutQueteDto {

    private UUID id;
    private String nom;
    private String description;
    private String difficulte;
    private LocalDate dateAssignation;
    private LocalDate dateEcheance;

}
