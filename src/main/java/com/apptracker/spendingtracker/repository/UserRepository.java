package com.apptracker.spendingtracker.repository;

import com.apptracker.spendingtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("""
    SELECT u FROM User u LEFT JOIN FETCH u.transactions WHERE u.userID = :userId
""")
    Optional<User> findUserWithTransactionsWithUserId(@Param("userId") Integer userId);

    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUserID(Integer userID);
}
