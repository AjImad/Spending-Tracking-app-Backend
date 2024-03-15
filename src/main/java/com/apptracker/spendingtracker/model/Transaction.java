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
public class Transaction {
    @Id
    @SequenceGenerator(name = "trans_seq_gen", sequenceName = "trans_seq_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_seq_gen")
    private Integer transactionID;
    @JsonIgnoreProperties("transactions")
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user; // Foreign key User
    private Long amount;
    private LocalDate date;
    private String note;
    @JsonIgnoreProperties("transactions")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category; // Foreign key Category
}
