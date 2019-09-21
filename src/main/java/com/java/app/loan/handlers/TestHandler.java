package com.java.app.loan.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
public class TestHandler {

    public Mono<ServerResponse> test(ServerRequest request) {
        HashMap<String, String> resp = new HashMap<>();
        resp.put("message", "this is test handler");

        // return response
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(resp));
    }
}
