package com.example.kaamelott.features.chevaliers.repositories;

import com.example.kaamelott.common.BaseRepository;
import com.example.kaamelott.features.chevaliers.entities.ChevalierEntity;
import com.example.kaamelott.features.quetes.entities.QueteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for accessing and managing ChevalierEntity data.
 * Extends BaseRepository for soft delete functionality.
 */
@Repository
public interface ChevalierRepository extends BaseRepository<ChevalierEntity, UUID> {

    @Query(
        """
        SELECT p.quete FROM ParticipationQueteEntity p
        WHERE p.chevalier.id = :chevalierId
        AND p.statutParticipation = 'EN_COURS'
        AND p.chevalier.deletedAt IS NULL
        AND p.quete.deletedAt IS NULL
        """
    )
    Page<QueteEntity> findQuetesEnCoursByChevalierId(UUID chevalierId, Pageable pageable);

}
