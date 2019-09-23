package com.java.app.loan.routers;

import com.java.app.loan.handlers.LoanHandler;
import com.java.app.loan.handlers.TestHandler;
import com.java.app.loan.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Component
public class Router {
    /***
     * transactionRouter specific router for transaction (loan) endpoint
     * @param loanHandler
     * @return
     */
    @Bean(name = "router-transaction")
    public RouterFunction<ServerResponse> transactionRouter(LoanHandler loanHandler) {
        return RouterFunctions.route(POST("/loan/transaction/new").and(accept(APPLICATION_JSON)), loanHandler::createTransaction)
                .andRoute(GET("/loan/transactions").and(accept(APPLICATION_JSON)), loanHandler::getTransactions)
                .andRoute(GET("/loan/all-transactions").and(accept(APPLICATION_JSON)), loanHandler::getAllTransactions)
                .andRoute(GET("/loan/transaction/interest/{interest}").and(accept(APPLICATION_JSON)), loanHandler::getTransactionByInterest)
                .andRoute(GET("/loan/transaction/term/{term}").and(accept(APPLICATION_JSON)), loanHandler::getTransactionByTerm);
    }

    /***
     * testRouter specific router for testing endpoint
     * @param testHandler
     * @return
     */
    @Bean(name = "router-test")
    public RouterFunction<ServerResponse> testRouter(TestHandler testHandler) {
        return RouterFunctions.route(GET("/loan/test")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), testHandler::test);
    }

    /***
     * userRouter specific router for user endpoint
     * @param userHandler
     * @return
     */
    @Bean(name = "router-user")
    public RouterFunction<ServerResponse> userRouter(UserHandler userHandler) {
        return RouterFunctions.route(POST("/loan/user/registration").and(accept(APPLICATION_JSON)), userHandler::userRegistration);
    }
}
