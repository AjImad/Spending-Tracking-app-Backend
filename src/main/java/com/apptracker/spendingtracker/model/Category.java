package com.apptracker.spendingtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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
    @Getter
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;
    private String categoryName;
//    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Transaction> transactions;
//    @JsonManagedReference
    @OneToMany(mappedBy = "category")
    private List<Budget> budgets;

}
