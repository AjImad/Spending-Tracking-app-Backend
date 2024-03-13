package com.apptracker.spendingtracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Long amount;
    private Date startDate;
    private Date endDate;
}
