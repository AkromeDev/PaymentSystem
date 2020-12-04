package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.PaymentSystemApp;
import com.mycompany.myapp.domain.MyTransaction;
import com.mycompany.myapp.repository.MyTransactionRepository;
import com.mycompany.myapp.service.MyTransactionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MyTransactionResource} REST controller.
 */
@SpringBootTest(classes = PaymentSystemApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class MyTransactionResourceIT {

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Long DEFAULT_AMOUNT = 1L;
    private static final Long UPDATED_AMOUNT = 2L;

    @Autowired
    private MyTransactionRepository myTransactionRepository;

    @Mock
    private MyTransactionRepository myTransactionRepositoryMock;

    @Mock
    private MyTransactionService myTransactionServiceMock;

    @Autowired
    private MyTransactionService myTransactionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMyTransactionMockMvc;

    private MyTransaction myTransaction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MyTransaction createEntity(EntityManager em) {
        MyTransaction myTransaction = new MyTransaction()
            .email(DEFAULT_EMAIL)
            .amount(DEFAULT_AMOUNT);
        return myTransaction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MyTransaction createUpdatedEntity(EntityManager em) {
        MyTransaction myTransaction = new MyTransaction()
            .email(UPDATED_EMAIL)
            .amount(UPDATED_AMOUNT);
        return myTransaction;
    }

    @BeforeEach
    public void initTest() {
        myTransaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createMyTransaction() throws Exception {
        int databaseSizeBeforeCreate = myTransactionRepository.findAll().size();
        // Create the MyTransaction
        restMyTransactionMockMvc.perform(post("/api/my-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(myTransaction)))
            .andExpect(status().isCreated());

        // Validate the MyTransaction in the database
        List<MyTransaction> myTransactionList = myTransactionRepository.findAll();
        assertThat(myTransactionList).hasSize(databaseSizeBeforeCreate + 1);
        MyTransaction testMyTransaction = myTransactionList.get(myTransactionList.size() - 1);
        assertThat(testMyTransaction.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testMyTransaction.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMyTransactionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = myTransactionRepository.findAll().size();

        // Create the MyTransaction with an existing ID
        myTransaction.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMyTransactionMockMvc.perform(post("/api/my-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(myTransaction)))
            .andExpect(status().isBadRequest());

        // Validate the MyTransaction in the database
        List<MyTransaction> myTransactionList = myTransactionRepository.findAll();
        assertThat(myTransactionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMyTransactions() throws Exception {
        // Initialize the database
        myTransactionRepository.saveAndFlush(myTransaction);

        // Get all the myTransactionList
        restMyTransactionMockMvc.perform(get("/api/my-transactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(myTransaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllMyTransactionsWithEagerRelationshipsIsEnabled() throws Exception {
        when(myTransactionServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMyTransactionMockMvc.perform(get("/api/my-transactions?eagerload=true"))
            .andExpect(status().isOk());

        verify(myTransactionServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllMyTransactionsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(myTransactionServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMyTransactionMockMvc.perform(get("/api/my-transactions?eagerload=true"))
            .andExpect(status().isOk());

        verify(myTransactionServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getMyTransaction() throws Exception {
        // Initialize the database
        myTransactionRepository.saveAndFlush(myTransaction);

        // Get the myTransaction
        restMyTransactionMockMvc.perform(get("/api/my-transactions/{id}", myTransaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(myTransaction.getId().intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingMyTransaction() throws Exception {
        // Get the myTransaction
        restMyTransactionMockMvc.perform(get("/api/my-transactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMyTransaction() throws Exception {
        // Initialize the database
        myTransactionService.save(myTransaction);

        int databaseSizeBeforeUpdate = myTransactionRepository.findAll().size();

        // Update the myTransaction
        MyTransaction updatedMyTransaction = myTransactionRepository.findById(myTransaction.getId()).get();
        // Disconnect from session so that the updates on updatedMyTransaction are not directly saved in db
        em.detach(updatedMyTransaction);
        updatedMyTransaction
            .email(UPDATED_EMAIL)
            .amount(UPDATED_AMOUNT);

        restMyTransactionMockMvc.perform(put("/api/my-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMyTransaction)))
            .andExpect(status().isOk());

        // Validate the MyTransaction in the database
        List<MyTransaction> myTransactionList = myTransactionRepository.findAll();
        assertThat(myTransactionList).hasSize(databaseSizeBeforeUpdate);
        MyTransaction testMyTransaction = myTransactionList.get(myTransactionList.size() - 1);
        assertThat(testMyTransaction.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testMyTransaction.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMyTransaction() throws Exception {
        int databaseSizeBeforeUpdate = myTransactionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMyTransactionMockMvc.perform(put("/api/my-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(myTransaction)))
            .andExpect(status().isBadRequest());

        // Validate the MyTransaction in the database
        List<MyTransaction> myTransactionList = myTransactionRepository.findAll();
        assertThat(myTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMyTransaction() throws Exception {
        // Initialize the database
        myTransactionService.save(myTransaction);

        int databaseSizeBeforeDelete = myTransactionRepository.findAll().size();

        // Delete the myTransaction
        restMyTransactionMockMvc.perform(delete("/api/my-transactions/{id}", myTransaction.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MyTransaction> myTransactionList = myTransactionRepository.findAll();
        assertThat(myTransactionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
