package com.apptracker.spendingtracker.service;

import com.apptracker.spendingtracker.model.Category;
import com.apptracker.spendingtracker.model.Transaction;
import com.apptracker.spendingtracker.model.User;
import com.apptracker.spendingtracker.repository.CategoryRepository;
import com.apptracker.spendingtracker.repository.TransactionRepository;
import com.apptracker.spendingtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Transaction udpateTransaction(Integer transactionId, Integer categoryId, Date date, Long amount) {
        Transaction storedTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with ID: " + transactionId));
        // check weather what the user want to update
        return null;
    }
}
