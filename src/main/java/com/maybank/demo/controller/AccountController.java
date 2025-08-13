package com.maybank.demo.controller;

import com.maybank.demo.entity.Account;
import com.maybank.demo.exception.AccountNotFoundException;
import com.maybank.demo.service.AccountService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@Valid @RequestBody Account account) {

        if (!accountService.createAccount(account)) {
            throw new RuntimeException("Account creation failed");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Account successfully created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountDetails(@PathVariable Long id) {

        Account account = accountService.findAccountById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));


        return ResponseEntity.ok(account);


    }

    @GetMapping
    public ResponseEntity<?> getAccounts(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        Page<Account> accountList = accountService.findAccounts(pageNumber, pageSize);


        return ResponseEntity.ok(accountList.getContent());


    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editAccountDetails(@PathVariable Long id,
                                                @RequestBody Account updatedAccount) {

        Account existingAccount = accountService.findAccountById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));


        existingAccount.setName(updatedAccount.getName() != null ? updatedAccount.getName() : existingAccount.getName());
        existingAccount.setEmail(updatedAccount.getEmail() != null ? updatedAccount.getEmail() : existingAccount.getEmail());
        existingAccount.setBalance(updatedAccount.getBalance() != null ? updatedAccount.getBalance() : existingAccount.getBalance());


        accountService.updateAccountDetails(existingAccount);

        return ResponseEntity.status(HttpStatus.OK).body("Account Details Updated");

    }
}

