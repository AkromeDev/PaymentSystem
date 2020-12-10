package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Buddy;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.BuddyService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Buddy}.
 */
@RestController
@RequestMapping("/api")
public class BuddyResource {
	
	private UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(BuddyResource.class);

    private static final String ENTITY_NAME = "buddy";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BuddyService buddyService;

    public BuddyResource(BuddyService buddyService) {
        this.buddyService = buddyService;
    }

    /**
     * {@code POST  /buddies} : Create a new buddy.
     *
     * @param buddy the buddy to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new buddy, or with status {@code 400 (Bad Request)} if the buddy has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/buddies")
    public ResponseEntity<Buddy> createBuddy(@Valid @RequestBody Buddy buddy) throws URISyntaxException {
        log.debug("REST request to save Buddy : {}", buddy);
        if (buddy.getId() != null) {
            throw new BadRequestAlertException("A new buddy cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Buddy result = buddyService.save(buddy);
        return ResponseEntity.created(new URI("/api/buddies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /buddies} : Updates an existing buddy.
     *
     * @param buddy the buddy to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated buddy,
     * or with status {@code 400 (Bad Request)} if the buddy is not valid,
     * or with status {@code 500 (Internal Server Error)} if the buddy couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/buddies")
    public ResponseEntity<Buddy> updateBuddy(@Valid @RequestBody Buddy buddy) throws URISyntaxException {
        log.debug("REST request to update Buddy : {}", buddy);
        if (buddy.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Buddy result = buddyService.save(buddy);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, buddy.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /buddies} : get all the buddies.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of buddies in body.
     */
    @GetMapping("/buddies")
    public ResponseEntity<List<Buddy>> getAllBuddies(Pageable pageable) {
        log.debug("REST request to get a page of Buddies");
        Page<Buddy> page = buddyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    /**
     * {@code GET  /buddies/view} : get the buddy of the connected user.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the buddy, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/buddies/view")
    public ResponseEntity<Buddy> getConnectedBuddy() {
        log.debug("REST request to get Buddy account of the connected user : {}");
        
        // TODO: Make this endpoint more beautiful, this is a mess!
        Optional<User> user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().toString());
        
        User nuser = user.get();
        
        Optional<Buddy> buddy = buddyService.findOne(nuser.getId());
        return ResponseUtil.wrapOrNotFound(buddy);
    }
    

    /**
     * {@code GET  /buddies/:id} : get the "id" buddy.
     *
     * @param id the id of the buddy to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the buddy, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/buddies/{id}")
    public ResponseEntity<Buddy> getBuddy(@PathVariable Long id) {
        log.debug("REST request to get Buddy : {}", id);
        Optional<Buddy> buddy = buddyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(buddy);
    }

    /**
     * {@code DELETE  /buddies/:id} : delete the "id" buddy.
     *
     * @param id the id of the buddy to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/buddies/{id}")
    public ResponseEntity<Void> deleteBuddy(@PathVariable Long id) {
        log.debug("REST request to delete Buddy : {}", id);
        buddyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
