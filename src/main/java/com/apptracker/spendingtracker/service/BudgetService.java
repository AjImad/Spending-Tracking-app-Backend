package com.apptracker.spendingtracker.service;

import com.apptracker.spendingtracker.model.Budget;
import com.apptracker.spendingtracker.model.Category;
import com.apptracker.spendingtracker.model.User;
import com.apptracker.spendingtracker.repository.BudgetRepository;
import com.apptracker.spendingtracker.repository.CategoryRepository;
import com.apptracker.spendingtracker.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public Budget createBudget(Budget budget) {
        User storedUser = userRepository.findUserByUserID(budget.getUser().getUserID())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + budget.getUser().getUserID()));
        Category storedCategory = categoryRepository.findById(budget.getCategory().getCategoryID())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + budget.getCategory().getCategoryID()));
        budget.setUser(storedUser);
        budget.setCategory(storedCategory);
        return budgetRepository.save(budget);
    }

    @Transactional
    public Budget updateBudget(Integer budgetId, Budget budget) {
        Budget storeBudget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new IllegalArgumentException("Budget not found with ID: " + budgetId));
        Category storedCategory = categoryRepository.findById(budget.getCategory().getCategoryID())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + budget.getCategory().getCategoryID()));
        if (
                budget.getCategory().getCategoryID() != null
                        && !storeBudget.getCategory().getCategoryID().equals(budget.getCategory().getCategoryID())
        ) {
            storeBudget.setCategory(storedCategory);
        }
        if(budget.getAmount() != null && !budget.getAmount().equals(storeBudget.getAmount())){
            storeBudget.setAmount(budget.getAmount());
        }
        if(budget.getStartDate() != null && !budget.getStartDate().equals(storeBudget.getStartDate())){
            storeBudget.setStartDate(budget.getStartDate());
        }
        if(budget.getEndDate() != null && !budget.getEndDate().equals(storeBudget.getEndDate())){
            storeBudget.setEndDate(budget.getEndDate());
        }

        return storeBudget;
    }

    public List<Budget> getAllBudgets(Integer userId) {
        userRepository.findUserByUserID(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        return budgetRepository.findAllBudgetWithUserId(userId)
                .orElse(null);
    }
}
