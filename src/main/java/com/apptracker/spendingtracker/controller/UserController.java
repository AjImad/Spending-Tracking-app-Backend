package com.apptracker.spendingtracker.controller;

import com.apptracker.spendingtracker.errorResponse.ErrorResponse;
import com.apptracker.spendingtracker.model.User;
import com.apptracker.spendingtracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            String path = "/api/users";
            ErrorResponse errorResponse = ErrorResponse.getErrorResponse(path, e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable Integer userId) {
        User user = userService.getUser(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            String path = "/api/users/" + userId;
            String message = "User not found with ID: " + userId;
            ErrorResponse errorResponse = ErrorResponse.getErrorResponse(path, message);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer userId, @RequestBody User user){
        try{
            User updatedUser = userService.updateUser(userId, user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            String path = "/api/users/" + userId;
            ErrorResponse errorResponse = ErrorResponse.getErrorResponse(path, e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<ErrorResponse> deleteUser(@PathVariable Integer userId){
        try{
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            String path = "/api/users/" + userId;
            ErrorResponse errorResponse = ErrorResponse.getErrorResponse(path, e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }

}
