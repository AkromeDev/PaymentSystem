package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Buddy;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Buddy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BuddyRepository extends JpaRepository<Buddy, Long> {
}
