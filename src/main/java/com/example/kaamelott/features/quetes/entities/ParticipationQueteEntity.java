package com.example.kaamelott.features.quetes.entities;

import com.example.kaamelott.features.chevaliers.entities.ChevalierEntity;
import com.example.kaamelott.features.quetes.enumerations.ParticipationQueteRole;
import com.example.kaamelott.features.quetes.enumerations.StatutParticipationQuete;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "participation_quetes")
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationQueteEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chevalier", nullable = false)
    private ChevalierEntity chevalier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_quete", nullable = false)
    private QueteEntity quete;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ParticipationQueteRole role;

    @Column(name = "statut_participation")
    @Enumerated(EnumType.STRING)
    private StatutParticipationQuete statutParticipation;

    @Column(name = "commentaire_roi")
    private String commentaireRoi;

}
