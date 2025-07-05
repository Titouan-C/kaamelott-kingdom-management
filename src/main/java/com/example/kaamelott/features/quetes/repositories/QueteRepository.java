package com.example.kaamelott.features.quetes.repositories;

import com.example.kaamelott.features.quetes.entities.ParticipationQueteEntity;
import com.example.kaamelott.features.quetes.entities.QueteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QueteRepository extends JpaRepository<QueteEntity, UUID> {

    @Query("SELECT pq FROM ParticipationQueteEntity pq WHERE pq.quete.id = :queteId")
    Page<ParticipationQueteEntity> findParticipantsByQueteId(UUID queteId, Pageable pageable);

}
