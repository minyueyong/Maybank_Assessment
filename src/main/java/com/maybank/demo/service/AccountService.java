package com.maybank.demo.service;

import com.maybank.demo.entity.Account;
import com.maybank.demo.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    Logger logger = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void updateAccountDetails (Account updatedAccount){
        accountRepository.save(updatedAccount);
    }

    public boolean createAccount (Account account ){
        try {
            accountRepository.save(account);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<Account> findAccountById (Long id){
        return accountRepository.findById(id);
    }

    public Page<Account> findAccounts (Integer pageNumber , Integer pageSize){


        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page <Account> products = accountRepository.findAll(paging);



        return products;
    }
}
