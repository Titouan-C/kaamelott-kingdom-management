package com.example.kaamelott.features.chevaliers.dtos.in;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InChevalierDto {

    @NotBlank(message = "Nom ne peut pas être vide")
    private String nom;

    @NotBlank(message = "Titre ne peut pas être vide")
    private String titre;

    @NotBlank(message = "Caractéristique principale ne peut pas être nulle")
    private String caracteristique_principale;

    @NotNull(message = "Niveau de bravoure ne peut pas être nul")
    @Min(value = 1, message = "Niveau de bravoure doit être supérieur ou égal à 1")
    @Max(value = 10, message = "Niveau de bravoure doit être inférieur ou égal à 10")
    private Integer niveau_bravoure;

}
