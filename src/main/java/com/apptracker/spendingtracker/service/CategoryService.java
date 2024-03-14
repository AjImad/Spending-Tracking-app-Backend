package com.apptracker.spendingtracker.service;

import com.apptracker.spendingtracker.model.Category;
import com.apptracker.spendingtracker.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        var storedCategory = categoryRepository.findCategoryByTypeAndName(category.getCategoryType(), category.getCategoryName());
        if(storedCategory.isPresent()){
            throw new IllegalArgumentException("This is category already taken!");
        }
        return categoryRepository.save(category);
    }
}
