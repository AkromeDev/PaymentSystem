package com.mycompany.myapp.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.myapp.domain.BankAccount;
import com.mycompany.myapp.domain.Buddy;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.BankAccountRepository;
import com.mycompany.myapp.repository.BuddyRepository;
import com.mycompany.myapp.service.BuddyService;

/**
 * Service Implementation for managing {@link Buddy}.
 */
@Service
@Transactional
public class BuddyServiceImpl implements BuddyService {

    private final Logger log = LoggerFactory.getLogger(BuddyServiceImpl.class);

    private final BuddyRepository buddyRepository;
    
    private final BankAccountRepository bankAccountRepo;

    public BuddyServiceImpl(BuddyRepository buddyRepository, BankAccountRepository bankAccountRepo) {
        this.buddyRepository = buddyRepository;
        this.bankAccountRepo = bankAccountRepo;
    }

    @Override
    public Buddy save(Buddy buddy) throws Exception {
        log.debug("Request to save Buddy : {}", buddy);
        if (buddy.getBalance() > 0) {
        	
        	Buddy originalBuddy = buddyRepository.findOneByFirstName(buddy.getFirstName()).get();
        	if (buddy.getBalance() != originalBuddy.getBalance()) {
        		
        		Long bic = buddyRepository.getBic(buddy.getId());
        		Long iban = buddyRepository.getBankAccount(buddy.getId());
        		if (bic==0 || iban==0) {
        			
        			throw new Exception ("You must first enter valid BankAccount information before adding funds to your Buddy Account");
        		}
        	}
        }
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
    
    @Override
    public Buddy createAutoBuddy(User user) {
        log.debug("Request to auto create Buddy : {}", user.getId());
        
        Buddy buddy = new Buddy();
        
        buddy.setFirstName(user.getLogin());
        buddy.setLastName("x");
        buddy.setEmail(user.getEmail());
        buddy.setId(user.getId());
        buddy.setBalance(0L);
        buddyRepository.save(buddy);
        
        createAutoBankAccount(user);
        
        return buddy;
    }
    
    @Override
    public void createAutoBankAccount(User user) {
    	log.debug("Request to auto create Bank Account with id : {}", user.getId());
    	
    	BankAccount bankAccount = new BankAccount();
    	
    	bankAccount.setBic(0L);
    	bankAccount.setIban(0L);
    	bankAccount.setName(user.getLogin());
    	bankAccount.setId(user.getId());
    	
    	bankAccountRepo.save(bankAccount);
    }

	@Override
	public Optional<Buddy> findOneByFirstName(String firstName) {
		log.debug("Request to find Buddy from first name: {}", firstName);
        return buddyRepository.findOneByFirstName(firstName);
    }
	
}
