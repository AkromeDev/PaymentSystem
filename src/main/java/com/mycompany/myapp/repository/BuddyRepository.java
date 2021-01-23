package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Buddy;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Buddy entity.
 */
//@SuppressWarnings("unused")
@Repository
public interface BuddyRepository extends JpaRepository<Buddy, Long> {

	@Query(value = "select buddy.* from BUDDY buddy where buddy.FIRST_NAME=:firstName", nativeQuery = true)
	Optional<Buddy> findOneByFirstName(@Param("firstName")String firstName);
}

