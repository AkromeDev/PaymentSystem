package com.mycompany.myapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.domain.Buddy;

/**
 * Spring Data  repository for the Buddy entity.
 */
//@SuppressWarnings("unused")
@Repository
public interface BuddyRepository extends JpaRepository<Buddy, Long> {

	@Query(value = "select buddy.* from BUDDY buddy where buddy.FIRST_NAME=:firstName", nativeQuery = true)
	Optional<Buddy> findOneByFirstName(@Param("firstName")String firstName);

	@Query(value = "select iban from BANK_ACCOUNT where BANK_ACCOUNT.id=:id", nativeQuery = true)
	Long getIban(@Param("id")Long id);
	
	@Query(value = "select bic from BANK_ACCOUNT where BANK_ACCOUNT.id=:id", nativeQuery = true)
	Long getBic(@Param("id")Long id);
}

