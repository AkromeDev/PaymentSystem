package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ContactRelationshipService;
import com.mycompany.myapp.domain.ContactRelationship;
import com.mycompany.myapp.repository.ContactRelationshipRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ContactRelationship}.
 */
@Service
@Transactional
public class ContactRelationshipServiceImpl implements ContactRelationshipService {

    private final Logger log = LoggerFactory.getLogger(ContactRelationshipServiceImpl.class);

    private final ContactRelationshipRepository contactRelationshipRepository;

    public ContactRelationshipServiceImpl(ContactRelationshipRepository contactRelationshipRepository) {
        this.contactRelationshipRepository = contactRelationshipRepository;
    }

    @Override
    public ContactRelationship save(ContactRelationship contactRelationship) {
        log.debug("Request to save ContactRelationship : {}", contactRelationship);
        return contactRelationshipRepository.save(contactRelationship);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactRelationship> findAll() {
        log.debug("Request to get all ContactRelationships");
        return contactRelationshipRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ContactRelationship> findOne(Long id) {
        log.debug("Request to get ContactRelationship : {}", id);
        return contactRelationshipRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ContactRelationship : {}", id);
        contactRelationshipRepository.deleteById(id);
    }
}
