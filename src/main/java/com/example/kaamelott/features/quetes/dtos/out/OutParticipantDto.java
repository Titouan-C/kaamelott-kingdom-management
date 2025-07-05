package com.example.kaamelott.features.quetes.dtos.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutParticipantDto {

    private UUID id;
    private String nom;
    private String role;
    private String statut;

}
