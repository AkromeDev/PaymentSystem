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
    
    @Override
    public Buddy createAutoBuddy(User user) {
        log.debug("Request to auto create Buddy : {}", user.getId());
        
        Buddy buddy = new Buddy();
        
        buddy.setFirstName(user.getLogin());
        buddy.setLastName("x");
        buddy.setEmail(user.getEmail());
        buddy.setId(user.getId());
        buddyRepository.save(buddy);
        
        createAutoBankAccount(user.getId());
        
        return buddy;
    }
    
    @Override
    public void createAutoBankAccount(Long id) {
    	log.debug("Request to auto create Bank Account with id : {}", id);
    	
    	BankAccount bankAccount = new BankAccount();
    	
    	bankAccount.setBic(00L);
    	bankAccount.setIban(00L);
    	bankAccount.setName("x");
    	bankAccount.setId(id);
    	
    	bankAccountRepo.save(bankAccount);
    }

	@Override
	public Optional<Buddy> findOneByFirstName(String firstName) {
		log.debug("Request to find Buddy from first name: {}", firstName);
        return buddyRepository.findOneByFirstName(firstName);
    }
	
}
