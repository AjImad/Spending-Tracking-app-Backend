package com.apptracker.spendingtracker.service;

import com.apptracker.spendingtracker.model.Category;
import com.apptracker.spendingtracker.model.Transaction;
import com.apptracker.spendingtracker.model.User;
import com.apptracker.spendingtracker.repository.CategoryRepository;
import com.apptracker.spendingtracker.repository.TransactionRepository;
import com.apptracker.spendingtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public Transaction addTransaction(Transaction transaction) {
        User storedUser = userRepository.findUserByUserID(transaction.getUser().getUserID())
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));
        Category storedCategory = categoryRepository.findById(transaction.getCategory().getCategoryID())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        transaction.setUser(storedUser);
        transaction.setCategory(storedCategory);
        return transactionRepository.save(transaction);
    }
}
