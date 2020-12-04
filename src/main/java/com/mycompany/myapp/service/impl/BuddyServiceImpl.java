package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.BuddyService;
import com.mycompany.myapp.domain.Buddy;
import com.mycompany.myapp.repository.BuddyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Buddy}.
 */
@Service
@Transactional
public class BuddyServiceImpl implements BuddyService {

    private final Logger log = LoggerFactory.getLogger(BuddyServiceImpl.class);

    private final BuddyRepository buddyRepository;

    public BuddyServiceImpl(BuddyRepository buddyRepository) {
        this.buddyRepository = buddyRepository;
    }

    @Override
    public Buddy save(Buddy buddy) {
        log.debug("Request to save Buddy : {}", buddy);
        return buddyRepository.save(buddy);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Buddy> findAll(Pageable pageable) {
        log.debug("Request to get all Buddies");
        return buddyRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Buddy> findOne(Long id) {
        log.debug("Request to get Buddy : {}", id);
        return buddyRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Buddy : {}", id);
        buddyRepository.deleteById(id);
    }
}
