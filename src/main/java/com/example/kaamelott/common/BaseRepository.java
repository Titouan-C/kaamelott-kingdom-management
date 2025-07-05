package com.example.kaamelott.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * Base repository interface for all entities with soft delete functionality.
 *
 * @param <T> the type of the entity
 * @param <ID> the type of the entity's identifier
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    @Query("SELECT COUNT(e) > 0 FROM #{#entityName} e WHERE e.id = :id AND e.deletedAt IS NULL")
    Boolean existsByIdNotDeleted(ID id);

    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NULL")
    Page<T> findAllNotDeleted(Pageable pageable);

    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id AND e.deletedAt IS NULL")
    Optional<T> findByIdAndNotDeleted(ID id);

}
