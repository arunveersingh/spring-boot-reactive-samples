package com.oopsfeedmecode.demo.controller;

import com.oopsfeedmecode.demo.model.UserRequest;
import com.oopsfeedmecode.demo.validation.annotations.Validate;
import com.oopsfeedmecode.demo.validation.validators.AgeValidator;
import com.oopsfeedmecode.demo.validation.validators.EmailValidator;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/user")
    @Validate({EmailValidator.class, AgeValidator.class})
    public Mono<ResponseEntity<String>> createUser(@Valid @RequestBody UserRequest request) {
        return Mono.just(ResponseEntity.ok("User created successfully"));
    }
}

