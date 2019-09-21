package com.java.app.loan.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
public class LoanHandler {
    /***
     * createTransaction
     * @param request
     * @return
     */
    public Mono<ServerResponse> createTransaction(ServerRequest request) {
        HashMap<String, String> resp = new HashMap<>();
        resp.put("message", "createTransaction");

        // return response
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(resp));
    }

    /***
     * getAllTransactions
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllTransactions(ServerRequest request) {
        HashMap<String, String> resp = new HashMap<>();
        resp.put("message", "getAllTransaction");

        // return response
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(resp));
    }

    /***
     * getTransactionByUserId
     * @param request
     * @return
     */
    public Mono<ServerResponse> getTransactionByUserId(ServerRequest request) {
        HashMap<String, String> resp = new HashMap<>();
        resp.put("message", "getTransactionByUserId");

        // return response
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(resp));
    }

    /***
     * getTransactionById
     * @param request
     * @return
     */
    public Mono<ServerResponse> getTransactionById(ServerRequest request) {
        HashMap<String, String> resp = new HashMap<>();
        resp.put("message", "getTransactionById");

        // return response
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(resp));
    }
}
