package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ContactRelationship;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ContactRelationship}.
 */
public interface ContactRelationshipService {

    /**
     * Save a contactRelationship.
     *
     * @param contactRelationship the entity to save.
     * @return the persisted entity.
     * @throws Exception if the email given by the user does not belong to a buddy account.
     */
    ContactRelationship save(ContactRelationship contactRelationship) throws Exception;

    /**
     * Get all the contactRelationships.
     *
     * @return the list of entities.
     */
    List<ContactRelationship> findAll();
    
    /**
     * Get all the contactRelationships from the current user.
     *
     * @return the list of entities.
     */
    List<ContactRelationship> findAllFromUser(Optional<Long> optional);


    /**
     * Get the "id" contactRelationship.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContactRelationship> findOne(Long id);

    /**
     * Delete the "id" contactRelationship.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
