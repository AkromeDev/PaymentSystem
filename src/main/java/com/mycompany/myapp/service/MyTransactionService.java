package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.MyTransaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link MyTransaction}.
 */
public interface MyTransactionService {

    /**
     * Save a myTransaction.
     *
     * @param myTransaction the entity to save.
     * @return the persisted entity.
     */
    MyTransaction save(MyTransaction myTransaction);

    /**
     * Get all the myTransactions.
     *
     * @return the list of entities.
     */
    List<MyTransaction> findAll();

    /**
     * Get all the myTransactions with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<MyTransaction> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" myTransaction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MyTransaction> findOne(Long id);

    /**
     * Delete the "id" myTransaction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
