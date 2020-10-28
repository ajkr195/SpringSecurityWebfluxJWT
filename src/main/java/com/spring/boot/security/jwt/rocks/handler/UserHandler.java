package com.spring.boot.security.jwt.rocks.handler;

import com.spring.boot.security.jwt.rocks.model.User;
import com.spring.boot.security.jwt.rocks.repo.UserRepository;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class UserHandler {

    private UserRepository userRepository;

    public UserHandler(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> createUser(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class).flatMap(user -> userRepository.save(user));
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(userMono, User.class);
    }

    public Mono<ServerResponse> listUser(ServerRequest request) {
        Flux<User> user = userRepository.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(user, User.class);
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        String userId = request.pathVariable("userId");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<User> userMono = userRepository.findById(userId);
        return userMono.flatMap(user -> ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromObject(user)))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        String userId = request.pathVariable("userId");
        userRepository.deleteById(userId);
        return ServerResponse.ok().build();
    }

}
