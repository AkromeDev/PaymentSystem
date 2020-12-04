package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.MyTransactionService;
import com.mycompany.myapp.domain.MyTransaction;
import com.mycompany.myapp.repository.MyTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link MyTransaction}.
 */
@Service
@Transactional
public class MyTransactionServiceImpl implements MyTransactionService {

    private final Logger log = LoggerFactory.getLogger(MyTransactionServiceImpl.class);

    private final MyTransactionRepository myTransactionRepository;

    public MyTransactionServiceImpl(MyTransactionRepository myTransactionRepository) {
        this.myTransactionRepository = myTransactionRepository;
    }

    @Override
    public MyTransaction save(MyTransaction myTransaction) {
        log.debug("Request to save MyTransaction : {}", myTransaction);
        return myTransactionRepository.save(myTransaction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MyTransaction> findAll() {
        log.debug("Request to get all MyTransactions");
        return myTransactionRepository.findAllWithEagerRelationships();
    }


    public Page<MyTransaction> findAllWithEagerRelationships(Pageable pageable) {
        return myTransactionRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MyTransaction> findOne(Long id) {
        log.debug("Request to get MyTransaction : {}", id);
        return myTransactionRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyTransaction : {}", id);
        myTransactionRepository.deleteById(id);
    }
}
