package com.apptracker.spendingtracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @SequenceGenerator(name = "catgry_seq_gen", sequenceName = "catgry_seq_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catgry_seq_gen")
    private Integer categoryID;
    private String categoryType;
    private String categoryName;
    @OneToMany(mappedBy = "category")
    private List<Transaction> transactions;
    @OneToMany(mappedBy = "category")
    private List<Budget> budgets;
}
