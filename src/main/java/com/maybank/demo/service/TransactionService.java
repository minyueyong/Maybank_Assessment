package com.maybank.demo.service;


import com.maybank.demo.entity.Account;
import com.maybank.demo.entity.Transaction;
import com.maybank.demo.exception.InsufficientBalanceException;
import com.maybank.demo.repository.AccountRepository;
import com.maybank.demo.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {


    Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;

    }

    @Transactional
    public void transferMoney(Long sourceAccountId, Long destinationAccountId, double amount) {

        addBalance(destinationAccountId, amount);
        deductBalance(sourceAccountId, amount);
        logTransaction(sourceAccountId, destinationAccountId, amount);
    }

    private void logTransaction(Long sourceAccountId, Long destinationAccountId, double amount){

        Transaction tx = new Transaction ();
        tx.setSourceAccountId(sourceAccountId);
        tx.setDestinationAccountId(destinationAccountId);
        tx.setAmount(amount);
        tx.setTimestamp(LocalDateTime.now());

        transactionRepository.save(tx);
    }

    private void addBalance ( Long destinationAccountId  , double amountTransferred ){

        Optional<Account> optionalAccount = accountRepository.findById(destinationAccountId);

        if (optionalAccount.isEmpty()){
            throw new RuntimeException("Account Not Found");
        }

        Account destinationAccount = optionalAccount.get();

        destinationAccount.setBalance(destinationAccount.getBalance() + amountTransferred);
        accountRepository.save(destinationAccount);

    }

    private void deductBalance (Long sourceAccountId , double amountTransferred){


        Optional<Account> optionalAccount = accountRepository.findById(sourceAccountId);


        if (optionalAccount.isEmpty()){
            throw new RuntimeException("Account Not Found");
        }

        Account destinationAccount = optionalAccount.get();

       if(destinationAccount.getBalance() < amountTransferred){
           throw new InsufficientBalanceException("Insufficient Balance to transfer");

       }else {
           destinationAccount.setBalance(destinationAccount.getBalance() - amountTransferred);

       }




    }
}
