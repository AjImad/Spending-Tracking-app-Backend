package com.apptracker.spendingtracker.service;

import com.apptracker.spendingtracker.model.User;
import com.apptracker.spendingtracker.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        var storedUser = userRepository.findUserByEmail(user.getEmail());
        if(storedUser.isPresent()){
            throw new IllegalArgumentException("User already exist with this email!");
        }

        return userRepository.save(user);
    }

    public User getUser(Integer userId) {
        return userRepository.findUserByUserID(userId).orElse(null);
    }

    @Transactional
    public User updateUser(Integer userId, User user) {
        User storedUser = userRepository.findUserByUserID(userId).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        if(user.getFirstname() != null && !user.getFirstname().isEmpty() && !Objects.equals(storedUser.getFirstname(), user.getFirstname())){
            storedUser.setFirstname(user.getFirstname());
        }
        if(user.getLastname() != null && !user.getLastname().isEmpty() && !Objects.equals(storedUser.getLastname(), user.getLastname())){
            storedUser.setLastname(user.getLastname());
        }
        if(user.getEmail() != null && !user.getEmail().isEmpty() && !Objects.equals(storedUser.getEmail(), user.getEmail())){
            storedUser.setEmail(user.getEmail());
        }
        if(user.getPassword() != null && !user.getPassword().isEmpty() && !Objects.equals(storedUser.getPassword(), user.getPassword())){
            storedUser.setPassword(user.getPassword());
        }
        System.out.println("storedUser: " + storedUser);
        return storedUser;
    }
}
