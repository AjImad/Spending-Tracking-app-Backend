package com.apptracker.spendingtracker.controller;

import com.apptracker.spendingtracker.errorResponse.ErrorResponse;
import com.apptracker.spendingtracker.model.Category;
import com.apptracker.spendingtracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("all-categories")
    public ResponseEntity<Object> getAllCategories(@RequestParam(required = false) String categoryType){
        List<Category> categories = categoryService.getAllCategories(categoryType);
        if(categories != null){
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        String path = "/api/categories/all-categories?" + categoryType;
        String message = "Category type must be either EXPENSE or INCOME";
        ErrorResponse errorResponse = ErrorResponse.getErrorResponse(path, message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

}
