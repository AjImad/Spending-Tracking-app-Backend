package com.apptracker.spendingtracker.service;

import com.apptracker.spendingtracker.model.Category;
import com.apptracker.spendingtracker.model.CategoryType;
import com.apptracker.spendingtracker.repository.CategoryRepository;
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
        System.out.println("categoryType: " + categoryType);
        if(categoryType == null){
            return categoryRepository.findAll();
        } else if  (!Objects.equals(categoryType, CategoryType.EXPENSE.name()) && !Objects.equals(categoryType, CategoryType.INCOME.name())){
            System.out.println("IS EQUAL: " + Objects.equals(categoryType, CategoryType.EXPENSE.name()));
            System.out.println("IS EQUAL: " + Objects.equals(categoryType, CategoryType.INCOME.name()));
            return null;
        } else {
            if (Objects.equals(categoryType, CategoryType.EXPENSE.name())){
                return categoryRepository.findAllCategoryByType(CategoryType.EXPENSE);
            }
            return categoryRepository.findAllCategoryByType(CategoryType.INCOME);
        }
    }
}
