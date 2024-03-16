package com.apptracker.spendingtracker.service;

import com.apptracker.spendingtracker.model.Category;
import com.apptracker.spendingtracker.model.CategoryType;
import com.apptracker.spendingtracker.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        var storedCategory = categoryRepository.findCategoryByTypeAndName(category.getCategoryType(), category.getCategoryName());
        if(storedCategory.isPresent()){
            throw new IllegalArgumentException("This category already taken!");
        }
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories(String categoryType) {
        if(categoryType == null){
            return categoryRepository.findAll();
        } else if  (!Objects.equals(categoryType, CategoryType.EXPENSE.name()) && !Objects.equals(categoryType, CategoryType.INCOME.name())){
            return null;
        } else {
            if (Objects.equals(categoryType, CategoryType.EXPENSE.name())){
                return categoryRepository.findAllCategoryByType(CategoryType.EXPENSE);
            }
            return categoryRepository.findAllCategoryByType(CategoryType.INCOME);
        }
    }

    @Transactional
    public Category updateCategory(Integer categoryId, String categoryName) {
        Category storedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found!"));
        if(categoryName != null && !Objects.equals(storedCategory.getCategoryName(), categoryName)){
            storedCategory.setCategoryName(categoryName);
        }
        return storedCategory;
    }

    public void deleteCategory(Integer categoryId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + categoryId));
        categoryRepository.deleteById(categoryId);
    }
}
