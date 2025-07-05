package com.example.kaamelott.features.quetes.repositories;

import com.example.kaamelott.common.BaseRepository;
import com.example.kaamelott.features.quetes.entities.ParticipationQueteEntity;
import com.example.kaamelott.features.quetes.entities.QueteEntity;
import com.example.kaamelott.features.quetes.enumerations.StatutParticipationQuete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing Quêtes in the Kaamelott application.
 * Extends BaseRepository for soft delete functionality.
 */
@Repository
public interface QueteRepository extends BaseRepository<QueteEntity, UUID> {

    @Query("""
    SELECT pq FROM ParticipationQueteEntity pq
    WHERE pq.quete.id = :queteId
    AND pq.chevalier.deletedAt IS NULL
    """)
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
            Pageable pageable
    );

}
