package com.apptracker.spendingtracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Budget {
    @Id
    @SequenceGenerator(name = "budgt_seq_gene", sequenceName = "budgt_seq_gene", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budgt_seq_gene")
    private Integer budgetID;
    @JsonIgnoreProperties("budgets")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @JsonIgnoreProperties("budgets")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Long amount;
    private LocalDate startDate;
    private LocalDate endDate;
}
