package com.java.app.loan.handlers;

import com.java.app.loan.domains.user.services.IUserService;
import com.java.app.loan.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.HashMap;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class UserHandler {

    @Autowired
    private IUserService userService;

    @Autowired
    private Scheduler scheduler;

    /***
     * userRegistration
     * @param request
     * @return
     */
    public Mono<ServerResponse> userRegistration(ServerRequest request) {
        // return response
        return request
                .bodyToMono(User.class)
                .flatMap(user -> Mono.fromCallable(() -> this.userService.create(user)))
                .publishOn(scheduler)
                .flatMap(userResponse -> {
                    if (userResponse.getError() != null)
                        return ServerResponse.badRequest().contentType(MediaType.APPLICATION_JSON).body(fromObject(userResponse));
                    else
                        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(userResponse));
                });
    }
}
