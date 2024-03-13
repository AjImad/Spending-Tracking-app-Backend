package com.apptracker.spendingtracker.repository;

import com.apptracker.spendingtracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
