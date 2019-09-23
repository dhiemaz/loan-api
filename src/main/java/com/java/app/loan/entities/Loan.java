package com.java.app.loan.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.app.loan.models.BaseRS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
@Data
public class Loan extends BaseRS implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 11)
    private int id;

    @JsonProperty("userId")
    @NotNull(message = "userId can't be null")
    @Column(name = "user_id")
    private int userId;

    @JsonProperty("amount")
    @NotNull(message = "amount can't be null")
    @Column(name = "amount")
    private BigDecimal amount;

    @JsonProperty("term")
    @NotNull(message = "term can't be null")
    @Column(name = "term", length = 2)
    private int term;

    @JsonProperty("termType")
    @NotNull(message = "termType can't be null")
    @Column(name = "term_type", length = 10)
    private String termType;

    @JsonProperty("issueDate")
    @NotNull(message = "issueDate can't be null")
    @Column(name = "issue_date")
    private Date issueDate;

    @JsonProperty("createdAt")
    @Column(name = "created_at")
    private Date createdAt;

    @JsonProperty("dueDate")
    @Column(name = "due_date")
    private Date dueDate;

    @JsonProperty("interest")
    @Column(name = "interest")
    private double interest;
}
