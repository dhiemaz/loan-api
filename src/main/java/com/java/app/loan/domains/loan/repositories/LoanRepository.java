package com.java.app.loan.domains.loan.repositories;

import com.java.app.loan.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface LoanRepository extends JpaRepository<Loan, BigInteger> {
    @Query(value = "SELECT " +
            "id AS loan_id, " +                     // 0
            "user_id, " +                           // 1
            "amount AS loan_amount, " +             // 2
            "term AS loan_term, " +                 // 3
            "term_type AS loan_term_type, " +       // 4
            "issue_date AS loan_issue_date, " +     // 5
            "due_date AS loan_due_date, " +         // 6
            "interest AS loan_interest, " +         // 7
            "FROM transactions WHERE issue_date = :issueDate AND due_date = :dueDate AND interest = :interest AND term = :term ",
            nativeQuery = true, name = "loanActiveTransaction")
    List<Loan> findLoanByParams(@Param("issueDate") Date issueDate,
                                @Param("dueDate") Date dueDate,
                                @Param("interest") double interest,
                                @Param("term") int term);


    List<Loan> findByInterest(double interest);
    List<Loan> findByTerm(int interest);
    List<Loan> findByIssueDateAndDueDate(Date issueDate, Date dueDate);
 }
