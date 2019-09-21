package com.java.app.loan.routers;

import com.java.app.loan.handlers.TestHandler;
import com.java.app.loan.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class UserRouter {
    /***
     * userRouter specific router for user endpoint
     * @param userHandler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> userRouter(UserHandler userHandler) {
        return RouterFunctions.route(GET("/loan/user/registration").and(accept(APPLICATION_JSON)), userHandler::userRegistration)
                .andRoute(GET("/loan/user/login").and(accept(APPLICATION_JSON)), userHandler::userLogin)
                .andRoute(GET("/loan/user/logout").and(accept(APPLICATION_JSON)), userHandler::userLogout);
    }
}
