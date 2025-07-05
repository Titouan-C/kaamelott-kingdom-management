package com.example.kaamelott.features.quetes.dtos.in;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InParticipantDto {

    @NotBlank(message = "ID du chevalier est obligatoire")
    @org.hibernate.validator.constraints.UUID(message = "ID du chevalier doit être un UUID valide")
    private UUID id_chevalier;

    @NotBlank(message = "Rôle est obligatoire")
    private String role;

    @NotBlank(message = "Statut est obligatoire")
    private String status;

}
