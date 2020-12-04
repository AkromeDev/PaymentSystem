package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ContactRelationship;
import com.mycompany.myapp.service.ContactRelationshipService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.ContactRelationship}.
 */
@RestController
@RequestMapping("/api")
public class ContactRelationshipResource {

    private final Logger log = LoggerFactory.getLogger(ContactRelationshipResource.class);

    private static final String ENTITY_NAME = "contactRelationship";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContactRelationshipService contactRelationshipService;

    public ContactRelationshipResource(ContactRelationshipService contactRelationshipService) {
        this.contactRelationshipService = contactRelationshipService;
    }

    /**
     * {@code POST  /contact-relationships} : Create a new contactRelationship.
     *
     * @param contactRelationship the contactRelationship to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contactRelationship, or with status {@code 400 (Bad Request)} if the contactRelationship has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contact-relationships")
    public ResponseEntity<ContactRelationship> createContactRelationship(@RequestBody ContactRelationship contactRelationship) throws URISyntaxException {
        log.debug("REST request to save ContactRelationship : {}", contactRelationship);
        if (contactRelationship.getId() != null) {
            throw new BadRequestAlertException("A new contactRelationship cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContactRelationship result = contactRelationshipService.save(contactRelationship);
        return ResponseEntity.created(new URI("/api/contact-relationships/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contact-relationships} : Updates an existing contactRelationship.
     *
     * @param contactRelationship the contactRelationship to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contactRelationship,
     * or with status {@code 400 (Bad Request)} if the contactRelationship is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contactRelationship couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contact-relationships")
    public ResponseEntity<ContactRelationship> updateContactRelationship(@RequestBody ContactRelationship contactRelationship) throws URISyntaxException {
        log.debug("REST request to update ContactRelationship : {}", contactRelationship);
        if (contactRelationship.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContactRelationship result = contactRelationshipService.save(contactRelationship);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, contactRelationship.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /contact-relationships} : get all the contactRelationships.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contactRelationships in body.
     */
    @GetMapping("/contact-relationships")
    public List<ContactRelationship> getAllContactRelationships() {
        log.debug("REST request to get all ContactRelationships");
        return contactRelationshipService.findAll();
    }

    /**
     * {@code GET  /contact-relationships/:id} : get the "id" contactRelationship.
     *
     * @param id the id of the contactRelationship to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contactRelationship, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contact-relationships/{id}")
    public ResponseEntity<ContactRelationship> getContactRelationship(@PathVariable Long id) {
        log.debug("REST request to get ContactRelationship : {}", id);
        Optional<ContactRelationship> contactRelationship = contactRelationshipService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contactRelationship);
    }

    /**
     * {@code DELETE  /contact-relationships/:id} : delete the "id" contactRelationship.
     *
     * @param id the id of the contactRelationship to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contact-relationships/{id}")
    public ResponseEntity<Void> deleteContactRelationship(@PathVariable Long id) {
        log.debug("REST request to delete ContactRelationship : {}", id);
        contactRelationshipService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
