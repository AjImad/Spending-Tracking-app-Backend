package com.apptracker.spendingtracker.repository;

import com.apptracker.spendingtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
