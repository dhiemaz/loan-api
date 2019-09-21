package com.java.app.loan.routers;

import com.java.app.loan.handlers.LoanHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class TransactionRouter {
    /***
     * transactionRouter specific router for transaction (loan) endpoint
     * @param loanHandler
     * @return
     */
    @Bean(name = "router-transaction")
    public RouterFunction<ServerResponse> transactionRouter(LoanHandler loanHandler) {
        return RouterFunctions.route(GET("/loan/transaction/new").and(accept(APPLICATION_JSON)), loanHandler::createTransaction)
                .andRoute(GET("/loan/transactions").and(accept(APPLICATION_JSON)), loanHandler::getAllTransactions)
                .andRoute(GET("/loan/transaction/user/{user_id}").and(accept(APPLICATION_JSON)), loanHandler::getTransactionByUserId)
                .andRoute(GET("/loan/transaction/{transaction_id}").and(accept(APPLICATION_JSON)), loanHandler::getTransactionById);
    }
}
