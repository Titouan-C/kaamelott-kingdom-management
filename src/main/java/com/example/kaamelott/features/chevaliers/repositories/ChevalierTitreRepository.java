package com.example.kaamelott.features.chevaliers.repositories;

import com.example.kaamelott.features.chevaliers.entities.ChevalierTitreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing and managing ChevalierTitreEntity data.
 */
@Repository
public interface ChevalierTitreRepository extends JpaRepository<ChevalierTitreEntity, UUID> {

    boolean existsByTitre(String titre);

    Optional<ChevalierTitreEntity> findByTitre(String titre);

}
