package com.java.app.loan.handlers;

import com.java.app.loan.domains.loan.models.LoanRequest;
import com.java.app.loan.domains.loan.services.ILoanService;
import com.java.app.loan.entities.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.Optional;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@Component
public class LoanHandler {

    @Autowired
    private ILoanService loanService;

    @Autowired
    private Scheduler scheduler;

    /***
     * createTransaction
     * @param request
     * @return
     */
    public Mono<ServerResponse> createTransaction(ServerRequest request) {
        // return response
        return request
                .bodyToMono(LoanRequest.class)
                .flatMap(loanRequest -> Mono.fromCallable(() -> this.loanService.createLoan(loanRequest)))
                .publishOn(scheduler)
                .flatMap(loanResponse -> {
                    if (loanResponse.getError() != null)
                        return ServerResponse.badRequest().contentType(MediaType.APPLICATION_JSON).body(fromObject(loanResponse));
                    else
                        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(loanResponse));
                });

    }

    /***
     * getAllTransactions
     * @param request
     * @return
     */
    public Mono<ServerResponse> getTransactions(ServerRequest request) {

        Optional<String> issueDate = request.queryParam("issueDate");
        Optional<String> dueDate = request.queryParam("dueDate");

        // return response
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(loanService.getTransactionsByIssueDateAndDueDate(issueDate.get(), dueDate.get()), Loan.class));
    }


    /***
     * getAllTransactions
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllTransactions(ServerRequest request) {
        // return response
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(loanService.getAllTransactions(), Loan.class));
    }

    /***
     * getTransactionByUserId
     * @param request
     * @return
     */
    public Mono<ServerResponse> getTransactionByTerm(ServerRequest request) {

        int term = Integer.parseInt(request.pathVariable("term"));

        // return response
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(loanService.getTransactionsByTerm(term), Loan.class));
    }

    /***
     * getTransactionById
     * @param request
     * @return
     */
    public Mono<ServerResponse> getTransactionByInterest(ServerRequest request) {

        double interest = Double.parseDouble(request.pathVariable("interest"));

        // return response
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(loanService.getTransactionsByInterest(interest), Loan.class));
    }
}
