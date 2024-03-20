package com.apptracker.spendingtracker.controller;

import com.apptracker.spendingtracker.errorResponse.ErrorResponse;
import com.apptracker.spendingtracker.model.Transaction;
import com.apptracker.spendingtracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/add-transaction")
    public ResponseEntity<Object> addTransaction(@RequestBody Transaction transaction){
        try{
            Transaction transaction1 = transactionService.addTransaction(transaction);
            return new ResponseEntity<>(transaction1, HttpStatus.OK);
        } catch(IllegalArgumentException e){
            String path = "/api/transactions/add-transaction";
            ErrorResponse errorResponse = ErrorResponse.getErrorResponse(path, e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }

    @GetMapping("/users/{users}")
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @DeleteMapping("{transactionId}")
    public ResponseEntity<ErrorResponse> deleteTransaction(@PathVariable Integer transactionId){
        try{
            transactionService.deleteTransaction(transactionId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e){
            String path = "/api/transactions/" + transactionId;
            ErrorResponse errorResponse = ErrorResponse.getErrorResponse(path, e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }

    @PutMapping("{transactionId}")
    public ResponseEntity<Object> updateTransaction(
            @PathVariable Integer transactionId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) Long amount,
            @RequestParam(required = false) String note
            ) {
        try{
            Transaction udpatedTransaction = transactionService.udpateTransaction(transactionId, categoryId, date, amount, note);
            return new ResponseEntity<>(udpatedTransaction, HttpStatus.OK);
        } catch(IllegalArgumentException e){
            String path = "/api/transactions/" + transactionId;
            ErrorResponse errorResponse = ErrorResponse.getErrorResponse(path, e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }
}
