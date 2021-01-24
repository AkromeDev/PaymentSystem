package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Buddy;
import com.mycompany.myapp.domain.ContactRelationship;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ContactRelationship entity.
 */
@Repository
public interface ContactRelationshipRepository extends JpaRepository<ContactRelationship, Long> {

	@Query(value = "select CONTACT_RELATIONSHIP.* from CONTACT_RELATIONSHIP CONTACT_RELATIONSHIP where CONTACT_RELATIONSHIP.id=:id", nativeQuery = true)
	List<ContactRelationship> findAllFromUser(@Param("id")Long id);
	
}
