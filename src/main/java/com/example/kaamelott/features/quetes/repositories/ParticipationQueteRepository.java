package com.example.kaamelott.features.quetes.repositories;

import com.example.kaamelott.features.quetes.entities.ParticipationQueteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing ParticipationQuete entities.
 */
@Repository
public interface ParticipationQueteRepository extends JpaRepository<ParticipationQueteEntity, UUID> {

    @Query("""
    SELECT COUNT(pq) > 0
    FROM ParticipationQueteEntity pq
    WHERE pq.chevalier.id = :chevalierId AND pq.quete.id = :queteId
    """)
    boolean existsByChevalierIdAndQueteId(UUID chevalierId, UUID queteId);

    @Modifying
    @Query("""
    DELETE FROM ParticipationQueteEntity pq
    WHERE pq.chevalier.id = :chevalierId AND pq.quete.id = :queteId
    """)
    void deleteByChevalierIdAndQueteId(UUID chevalierId, UUID queteId);

}
