package com.example.kaamelott.features.chevaliers.entities;

import com.example.kaamelott.features.chevaliers.enumerations.CaracteristiquePrincipale;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "chevaliers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChevalierEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_titre", nullable = false)
    private ChevalierTitreEntity titre;

    @Column(name = "caracteristique_principale")
    @Enumerated(EnumType.STRING)
    private CaracteristiquePrincipale caracteristiquePrincipale;

    @Column(name = "niveau_bravoure", nullable = false)
    @Min(1)
    @Max(10)
    private Integer niveauBravoure;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
