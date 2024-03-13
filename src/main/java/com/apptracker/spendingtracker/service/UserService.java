package com.apptracker.spendingtracker.service;

import com.apptracker.spendingtracker.model.User;
import com.apptracker.spendingtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
