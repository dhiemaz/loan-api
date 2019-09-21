package com.java.app.loan.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
public class UserHandler {
    /***
     * userLogin
     * @param request
     * @return
     */
    public Mono<ServerResponse> userLogin(ServerRequest request) {
        HashMap<String, String> resp = new HashMap<>();
        resp.put("message", "userLogin");

        // return response
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(resp));
    }

    /***
     * userLogout
     * @param request
     * @return
     */
    public Mono<ServerResponse> userLogout(ServerRequest request) {
        HashMap<String, String> resp = new HashMap<>();
        resp.put("message", "userLogout");

        // return response
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(resp));
    }

    /***
     * userRegistration
     * @param request
     * @return
     */
    public Mono<ServerResponse> userRegistration(ServerRequest request) {
        HashMap<String, String> resp = new HashMap<>();
        resp.put("message", "userRegistration");

        // return response
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(resp));
    }
}
