package com.apptracker.spendingtracker.controller;

import com.apptracker.spendingtracker.errorResponse.ErrorResponse;
import com.apptracker.spendingtracker.model.Category;
import com.apptracker.spendingtracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add-category")
    public ResponseEntity<Object> addCategory(@RequestBody Category category){
        try{
            Category newCategory = categoryService.addCategory(category);
            return new ResponseEntity<>(newCategory, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            String path = "/api/categories/add-category";
            ErrorResponse errorResponse = ErrorResponse.getErrorResponse(path, e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(errorResponse);
        }
    }

}
