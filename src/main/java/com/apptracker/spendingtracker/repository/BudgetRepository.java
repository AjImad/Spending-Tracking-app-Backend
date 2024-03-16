package com.apptracker.spendingtracker.repository;

import com.apptracker.spendingtracker.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    //    @Query("""
//    select b from Budget b where b.user.userID = :userId
//    """)
    @Query("""
        select b from Budget b inner join User u on b.user.userID = u.userID where b.user.userID = :userId
    """)
    Optional<List<Budget>> findAllBudgetWithUserId(Integer userId);
}
