package com.mycompany.myapp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.myapp.domain.Buddy;
import com.mycompany.myapp.domain.MyTransaction;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.MyTransactionService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.MyTransaction}.
 */
@RestController
@RequestMapping("/api")
public class MyTransactionResource {

    private final Logger log = LoggerFactory.getLogger(MyTransactionResource.class);

    private static final String ENTITY_NAME = "myTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MyTransactionService myTransactionService;
    
    private Buddy buddy = new Buddy();

    public MyTransactionResource(MyTransactionService myTransactionService) {
        this.myTransactionService = myTransactionService;
    }

    /**
     * {@code POST  /my-transactions} : Create a new myTransaction.
     *
     * @param myTransaction the myTransaction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new myTransaction, or with status {@code 400 (Bad Request)} if the myTransaction has already an ID.
     * @throws Exception is thrown when the balance of the user is lower than the amount of money he tries to send.
     */
    @PostMapping("/my-transactions")
    public ResponseEntity<MyTransaction> createMyTransaction(@RequestBody MyTransaction myTransaction) throws Exception {
        log.debug("REST request to save MyTransaction : {}", myTransaction);
        if (myTransaction.getId() != null) {
            throw new BadRequestAlertException("A new myTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyTransaction result = myTransactionService.save(myTransaction);
        return ResponseEntity.created(new URI("/api/my-transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /my-transactions} : Updates an existing myTransaction.
     *
     * @param myTransaction the myTransaction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated myTransaction,
     * or with status {@code 400 (Bad Request)} if the myTransaction is not valid,
     * or with status {@code 500 (Internal Server Error)} if the myTransaction couldn't be updated.
     * @throws Exception is thrown when the balance of the user is lower than the amount of money he tries to send.
     */
    @PutMapping("/my-transactions")
    public ResponseEntity<MyTransaction> updateMyTransaction(@RequestBody MyTransaction myTransaction) throws Exception {
        log.debug("REST request to update MyTransaction : {}", myTransaction);
        if (myTransaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MyTransaction result = myTransactionService.save(myTransaction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, myTransaction.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /my-transactions} : get all the myTransactions.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of myTransactions in body.
     */
    // TODO the s was taken away, that will screw up the tests. Fix them!
    @GetMapping("/my-transaction")
    public List<MyTransaction> getAllMyTransactions(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all MyTransactions");
        return myTransactionService.findAll();
    }
    
    /**
     * {@code GET  /my-transactions} : get all the myTransactions.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of myTransactions in body.
     */
    @GetMapping("/my-transactions")
    public List<MyTransaction> getAllMyTransactionsFromCurrentUser(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all MyTransactions");
        return myTransactionService.findAllFromUser(SecurityUtils.getCurrentUserId());
    }

    /**
     * {@code GET  /my-transactions/:id} : get the "id" myTransaction.
     *
     * @param id the id of the myTransaction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the myTransaction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/my-transactions/{id}")
    public ResponseEntity<MyTransaction> getMyTransaction(@PathVariable Long id) {
        log.debug("REST request to get MyTransaction : {}", id);
        Optional<MyTransaction> myTransaction = myTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(myTransaction);
    }

    /**
     * {@code DELETE  /my-transactions/:id} : delete the "id" myTransaction.
     *
     * @param id the id of the myTransaction to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/my-transactions/{id}")
    public ResponseEntity<Void> deleteMyTransaction(@PathVariable Long id) {
        log.debug("REST request to delete MyTransaction : {}", id);
        myTransactionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
    
    @PostMapping("/make-transactions")
    public ResponseEntity<MyTransaction> makeMyTransaction(@RequestBody MyTransaction myTransaction) throws Exception {
        log.debug("REST request to make a MyTransaction : {}", myTransaction);
        if (myTransaction.getId() != null) {
            throw new BadRequestAlertException("A new myTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        
        if (buddy.getBalance() > myTransaction.getAmount()){
        	
        	buddy.setBalance(buddy.getBalance() - myTransaction.getAmount());
        	
        } else {
        	throw new BadRequestAlertException("Your Balance is not sufficient in order to make this transaction", ENTITY_NAME, "indexists"); 
        }
        MyTransaction result = myTransactionService.save(myTransaction);
        
        
        return ResponseEntity.created(new URI("/api/my-transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
