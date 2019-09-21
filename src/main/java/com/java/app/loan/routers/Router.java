package com.java.app.loan.routers;

import com.java.app.loan.handlers.TestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class Router {
    @Bean
    public RouterFunction<ServerResponse> route(TestHandler testHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/loan/test")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), testHandler::test);
    }
}
