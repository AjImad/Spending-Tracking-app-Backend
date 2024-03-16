package com.apptracker.spendingtracker.controller;

import com.apptracker.spendingtracker.errorResponse.ErrorResponse;
import com.apptracker.spendingtracker.model.Budget;
import com.apptracker.spendingtracker.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping("add-budget")
    public ResponseEntity<Object> createBudget(@RequestBody Budget budget){
        try{
            Budget newBudget = budgetService.createBudget(budget);
            return new ResponseEntity<>(newBudget, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            String path = "/api/budgets/add-budget";
            ErrorResponse errorResponse = ErrorResponse.getErrorResponse(path, e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }

    @PutMapping("{budgetId}")
    public ResponseEntity<Object> updateBudget(@PathVariable Integer budgetId, @RequestBody Budget budget){
        try{
            Budget newBudget = budgetService.updateBudget(budgetId, budget);
            return new ResponseEntity<>(newBudget, HttpStatus.OK);
        } catch(IllegalArgumentException e){
            String path = "/api/budgets/" + budgetId;
            ErrorResponse errorResponse = ErrorResponse.getErrorResponse(path, e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }
}
