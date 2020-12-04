package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.PaymentSystemApp;
import com.mycompany.myapp.domain.Buddy;
import com.mycompany.myapp.repository.BuddyRepository;
import com.mycompany.myapp.service.BuddyService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BuddyResource} REST controller.
 */
@SpringBootTest(classes = PaymentSystemApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BuddyResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Long DEFAULT_PHONE_NUMBER = 1L;
    private static final Long UPDATED_PHONE_NUMBER = 2L;

    private static final Long DEFAULT_BALANCE = 1L;
    private static final Long UPDATED_BALANCE = 2L;

    @Autowired
    private BuddyRepository buddyRepository;

    @Autowired
    private BuddyService buddyService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBuddyMockMvc;

    private Buddy buddy;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Buddy createEntity(EntityManager em) {
        Buddy buddy = new Buddy()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .balance(DEFAULT_BALANCE);
        return buddy;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Buddy createUpdatedEntity(EntityManager em) {
        Buddy buddy = new Buddy()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .balance(UPDATED_BALANCE);
        return buddy;
    }

    @BeforeEach
    public void initTest() {
        buddy = createEntity(em);
    }

    @Test
    @Transactional
    public void createBuddy() throws Exception {
        int databaseSizeBeforeCreate = buddyRepository.findAll().size();
        // Create the Buddy
        restBuddyMockMvc.perform(post("/api/buddies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(buddy)))
            .andExpect(status().isCreated());

        // Validate the Buddy in the database
        List<Buddy> buddyList = buddyRepository.findAll();
        assertThat(buddyList).hasSize(databaseSizeBeforeCreate + 1);
        Buddy testBuddy = buddyList.get(buddyList.size() - 1);
        assertThat(testBuddy.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testBuddy.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testBuddy.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testBuddy.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testBuddy.getBalance()).isEqualTo(DEFAULT_BALANCE);
    }

    @Test
    @Transactional
    public void createBuddyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = buddyRepository.findAll().size();

        // Create the Buddy with an existing ID
        buddy.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBuddyMockMvc.perform(post("/api/buddies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(buddy)))
            .andExpect(status().isBadRequest());

        // Validate the Buddy in the database
        List<Buddy> buddyList = buddyRepository.findAll();
        assertThat(buddyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = buddyRepository.findAll().size();
        // set the field null
        buddy.setFirstName(null);

        // Create the Buddy, which fails.


        restBuddyMockMvc.perform(post("/api/buddies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(buddy)))
            .andExpect(status().isBadRequest());

        List<Buddy> buddyList = buddyRepository.findAll();
        assertThat(buddyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = buddyRepository.findAll().size();
        // set the field null
        buddy.setLastName(null);

        // Create the Buddy, which fails.


        restBuddyMockMvc.perform(post("/api/buddies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(buddy)))
            .andExpect(status().isBadRequest());

        List<Buddy> buddyList = buddyRepository.findAll();
        assertThat(buddyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = buddyRepository.findAll().size();
        // set the field null
        buddy.setEmail(null);

        // Create the Buddy, which fails.


        restBuddyMockMvc.perform(post("/api/buddies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(buddy)))
            .andExpect(status().isBadRequest());

        List<Buddy> buddyList = buddyRepository.findAll();
        assertThat(buddyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBuddies() throws Exception {
        // Initialize the database
        buddyRepository.saveAndFlush(buddy);

        // Get all the buddyList
        restBuddyMockMvc.perform(get("/api/buddies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(buddy.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE.intValue())));
    }
    
    @Test
    @Transactional
    public void getBuddy() throws Exception {
        // Initialize the database
        buddyRepository.saveAndFlush(buddy);

        // Get the buddy
        restBuddyMockMvc.perform(get("/api/buddies/{id}", buddy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(buddy.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.intValue()))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingBuddy() throws Exception {
        // Get the buddy
        restBuddyMockMvc.perform(get("/api/buddies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBuddy() throws Exception {
        // Initialize the database
        buddyService.save(buddy);

        int databaseSizeBeforeUpdate = buddyRepository.findAll().size();

        // Update the buddy
        Buddy updatedBuddy = buddyRepository.findById(buddy.getId()).get();
        // Disconnect from session so that the updates on updatedBuddy are not directly saved in db
        em.detach(updatedBuddy);
        updatedBuddy
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .balance(UPDATED_BALANCE);

        restBuddyMockMvc.perform(put("/api/buddies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBuddy)))
            .andExpect(status().isOk());

        // Validate the Buddy in the database
        List<Buddy> buddyList = buddyRepository.findAll();
        assertThat(buddyList).hasSize(databaseSizeBeforeUpdate);
        Buddy testBuddy = buddyList.get(buddyList.size() - 1);
        assertThat(testBuddy.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testBuddy.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testBuddy.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testBuddy.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testBuddy.getBalance()).isEqualTo(UPDATED_BALANCE);
    }

    @Test
    @Transactional
    public void updateNonExistingBuddy() throws Exception {
        int databaseSizeBeforeUpdate = buddyRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBuddyMockMvc.perform(put("/api/buddies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(buddy)))
            .andExpect(status().isBadRequest());

        // Validate the Buddy in the database
        List<Buddy> buddyList = buddyRepository.findAll();
        assertThat(buddyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBuddy() throws Exception {
        // Initialize the database
        buddyService.save(buddy);

        int databaseSizeBeforeDelete = buddyRepository.findAll().size();

        // Delete the buddy
        restBuddyMockMvc.perform(delete("/api/buddies/{id}", buddy.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Buddy> buddyList = buddyRepository.findAll();
        assertThat(buddyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
