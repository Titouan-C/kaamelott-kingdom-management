package com.example.kaamelott.features.quetes.repositories;

import com.example.kaamelott.features.quetes.entities.ParticipationQueteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing ParticipationQuete entities.
 */
@Repository
public interface ParticipationQueteRepository extends JpaRepository<ParticipationQueteEntity, UUID> {

}
