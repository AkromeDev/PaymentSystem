package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Buddy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Buddy}.
 */
public interface BuddyService {

    /**
     * Save a buddy.
     *
     * @param buddy the entity to save.
     * @return the persisted entity.
     */
    Buddy save(Buddy buddy);

    /**
     * Get all the buddies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Buddy> findAll(Pageable pageable);


    /**
     * Get the "id" buddy.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Buddy> findOne(Long id);

    /**
     * Delete the "id" buddy.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
