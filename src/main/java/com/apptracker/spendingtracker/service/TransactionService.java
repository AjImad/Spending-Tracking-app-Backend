package com.apptracker.spendingtracker.service;

import com.apptracker.spendingtracker.model.Category;
import com.apptracker.spendingtracker.model.Transaction;
import com.apptracker.spendingtracker.model.User;
import com.apptracker.spendingtracker.repository.CategoryRepository;
import com.apptracker.spendingtracker.repository.TransactionRepository;
import com.apptracker.spendingtracker.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public Transaction addTransaction(Transaction transaction) {
        User storedUser = userRepository.findUserByUserID(transaction.getUser().getUserID())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + transaction.getUser().getUserID()));
        Category storedCategory = categoryRepository.findById(transaction.getCategory().getCategoryID())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + transaction.getCategory().getCategoryID()));
        transaction.setUser(storedUser);
        transaction.setCategory(storedCategory);
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Integer transactionId) {
        transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with ID: " + transactionId));
        transactionRepository.deleteById(transactionId);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Transactional
    public Transaction udpateTransaction(Integer transactionId, Integer categoryId, LocalDate date, Long amount, String note) {
        Transaction storedTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with ID: " + transactionId));

        if(categoryId != null){
            Category storedCategory = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + categoryId));
            System.out.println("Is category id equals? " + !categoryId.equals(storedCategory.getCategoryID()) + storedCategory.getCategoryID());
            if(!categoryId.equals(storedTransaction.getCategory().getCategoryID())){
                storedTransaction.setCategory(storedCategory);
            }
        }
        if (date != null && date != storedTransaction.getDate()) {
            System.out.println("Given date: " + date);
            storedTransaction.setDate(date);
        }
        if(amount != null && !amount.equals(storedTransaction.getAmount())){
            storedTransaction.setAmount(amount);
        }
        if(note != null && !note.equals(storedTransaction.getNote())){
            storedTransaction.setNote(note);
        }
        return storedTransaction;
    }
}
