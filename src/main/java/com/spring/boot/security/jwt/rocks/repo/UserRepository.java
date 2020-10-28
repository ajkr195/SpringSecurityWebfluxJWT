package com.spring.boot.security.jwt.rocks.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.spring.boot.security.jwt.rocks.model.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findByUsername(String username);
}
