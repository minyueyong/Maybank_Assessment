package com.maybank.demo.controller;

import com.maybank.demo.dto.TransferRequest;
import com.maybank.demo.service.AccountService;
import com.maybank.demo.service.TransactionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    Logger logger = LoggerFactory.getLogger(TransactionController.class);


    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;

    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferMoney (@Valid @RequestBody TransferRequest request){

      transactionService.transferMoney(request.getSourceAccountId(),
              request.getDestinationAccountId(), request.getAmount());

        return ResponseEntity.status(HttpStatus.OK).body("Money Successfully Transferred");
    }

}
