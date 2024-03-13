package com.apptracker.spendingtracker.repository;

import com.apptracker.spendingtracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
