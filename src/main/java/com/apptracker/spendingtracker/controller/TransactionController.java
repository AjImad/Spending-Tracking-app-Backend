package com.apptracker.spendingtracker.controller;

import com.apptracker.spendingtracker.model.Transaction;
import com.apptracker.spendingtracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/add-transaction")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction){
        Transaction transaction1 = transactionService.addTransaction(transaction);
        return new ResponseEntity<>(transaction1, HttpStatus.OK);
    }
}
