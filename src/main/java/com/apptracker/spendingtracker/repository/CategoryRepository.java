package com.apptracker.spendingtracker.repository;

import com.apptracker.spendingtracker.model.Category;
import com.apptracker.spendingtracker.model.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("""
    select c from Category c where c.categoryType = :cType and c.categoryName = :cName
""")
    Optional<Category> findCategoryByTypeAndName(CategoryType cType, String cName);

    Optional<Category> findCategoryBycategoryName(String categoryName);
}
