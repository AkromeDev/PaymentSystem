package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ContactRelationship;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ContactRelationship entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactRelationshipRepository extends JpaRepository<ContactRelationship, Long> {
}
