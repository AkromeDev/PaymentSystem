package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ContactRelationshipService;
import com.mycompany.myapp.domain.ContactRelationship;
import com.mycompany.myapp.repository.ContactRelationshipRepository;
import com.mycompany.myapp.security.SecurityUtils;

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
    public ContactRelationship save(ContactRelationship contactRelationship) throws Exception {
        log.debug("Request to save ContactRelationship : {}", contactRelationship);
        contactRelationship.setUserId1(SecurityUtils.getCurrentUserId().get());
        
        String userEmail = contactRelationshipRepository.checkIfEmailExists(contactRelationship.getEmail());
        
        if (userEmail != null) {
        contactRelationship.setEmail(userEmail);
        contactRelationship.setName(contactRelationshipRepository.getNameFromEmail(contactRelationship.getEmail()));
        
        } else {
        	throw new Exception ("There is no no account registered under: " + userEmail);
        }
        
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
    public List<ContactRelationship> findAllFromUser(Optional<Long> id) {
    	log.debug("Request to get all ContactRelationships from the current user");
    	return contactRelationshipRepository.findAllFromUser(id.get());
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
