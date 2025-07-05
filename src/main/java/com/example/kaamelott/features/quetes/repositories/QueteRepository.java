package com.example.kaamelott.features.quetes.repositories;

import com.example.kaamelott.features.quetes.entities.ParticipationQueteEntity;
import com.example.kaamelott.features.quetes.entities.QueteEntity;
import com.example.kaamelott.features.quetes.enumerations.StatutParticipationQuete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QueteRepository extends JpaRepository<QueteEntity, UUID> {

    @Query("SELECT pq FROM ParticipationQueteEntity pq WHERE pq.quete.id = :queteId")
    Page<ParticipationQueteEntity> findParticipantsByQueteId(UUID queteId, Pageable pageable);

    @Query("""
            SELECT q FROM QueteEntity q
            JOIN q.participationQueteEntities pq
            WHERE q.difficulte = 'ABERRANTE'
            AND pq.statutParticipation NOT IN :finishedStatuses
            AND q.deletedAt IS NULL
    """)
    Page<QueteEntity> findQuetesDifficulteAberrante(
            List<StatutParticipationQuete> finishedStatuses,
            Pageable pageable);

}
