package com.apptracker.spendingtracker.controller;

import com.apptracker.spendingtracker.model.User;
import com.apptracker.spendingtracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // If there are validations errors, collect error message and return the first one.
            StringBuilder errorMessage = new StringBuilder();
            for(FieldError error: bindingResult.getFieldErrors()){
                errorMessage.append(error.getDefaultMessage());
                break;
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
        try{
            User newUser = userService.createUser(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(newUser);
        } catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Failed to create user: " + e.getMessage());
        }
    }
}
