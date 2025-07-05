package com.example.kaamelott.features.chevaliers.dtos.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Data Transfer Object for representing a Chevalier in the Kaamelott application.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutChevalierDto {

    private UUID id;
    private String nom;
    private String titre;
    private String caracteristique_principale;
    private Integer niveau_bravoure;

}
