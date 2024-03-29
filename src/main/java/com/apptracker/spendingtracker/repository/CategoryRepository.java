package com.apptracker.spendingtracker.repository;

import com.apptracker.spendingtracker.model.Category;
import com.apptracker.spendingtracker.model.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("""
    select c from Category c where c.categoryType = :cType and c.categoryName = :cName
    """)
    Optional<Category> findCategoryByTypeAndName(CategoryType cType, String cName);

    Optional<Category> findCategoryBycategoryName(String categoryName);

    @Query("""
    select c from Category c where c.categoryType = :categoryType
    """)
    List<Category> findAllCategoryByType(@Param("categoryType") CategoryType categoryType);
}
