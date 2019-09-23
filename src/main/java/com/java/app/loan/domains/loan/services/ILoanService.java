package com.java.app.loan.domains.loan.services;

import com.java.app.loan.domains.loan.models.LoanRequest;
import com.java.app.loan.domains.loan.models.LoanResponse;
import com.java.app.loan.entities.Loan;
import reactor.core.publisher.Flux;

public interface ILoanService {
    LoanResponse createLoan(LoanRequest loanRequest);
    Flux<Loan> getTransactions(String issueDate, String dueDate, double interest, int term);
    Flux<Loan> getAllTransactions();
    Flux<Loan> getTransactionsByInterest(double interest);
    Flux<Loan> getTransactionsByTerm(int term);
    Flux<Loan> getTransactionsByIssueDateAndDueDate(String issueDate, String dueDate);
}
