package com.example.kaamelott.features.quetes.entities;

import com.example.kaamelott.features.quetes.enumerations.DifficulteQuete;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "quetes")
@NoArgsConstructor
@AllArgsConstructor
public class QueteEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "nom_quete", nullable = false)
    private String nom_quete;

    @Column(name = "description")
    private String description;

    @Column(name = "difficulte", nullable = false)
    @Enumerated(EnumType.STRING)
    private DifficulteQuete difficulte;

    @Column(name = "date_assignation", nullable = false)
    private LocalDate dateAssignation;

    @Column(name = "date_echeance", nullable = false)
    private LocalDate dateEcheance;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "participation_quetes",
        joinColumns = @JoinColumn(name = "id_quete"),
        inverseJoinColumns = @JoinColumn(name = "id_chevalier")
    )
    private Set<ParticipationQueteEntity> participationQueteEntities;

}
