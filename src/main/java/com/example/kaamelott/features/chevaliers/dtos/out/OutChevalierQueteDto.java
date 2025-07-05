package com.example.kaamelott.features.chevaliers.dtos.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutChevalierQueteDto {

    private UUID quete_id;
    private String quete_nom;
    private String participant_statut;

}
