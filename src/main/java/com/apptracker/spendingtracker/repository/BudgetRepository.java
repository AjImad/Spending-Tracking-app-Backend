package com.apptracker.spendingtracker.repository;

import com.apptracker.spendingtracker.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {
}
