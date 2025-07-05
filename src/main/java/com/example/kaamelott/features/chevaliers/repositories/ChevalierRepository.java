package com.example.kaamelott.features.chevaliers.repositories;

import com.example.kaamelott.features.chevaliers.entities.ChevalierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for accessing and managing ChevalierEntity data.
 */
@Repository
public interface ChevalierRepository extends JpaRepository<ChevalierEntity, UUID> {
}
