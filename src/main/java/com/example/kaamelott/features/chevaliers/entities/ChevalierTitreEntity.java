package com.example.kaamelott.features.chevaliers.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "chevalier_titres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChevalierTitreEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "titre", nullable = false)
    private String titre;

}
