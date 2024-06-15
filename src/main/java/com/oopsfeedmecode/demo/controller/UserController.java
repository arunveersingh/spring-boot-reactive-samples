package com.oopsfeedmecode.demo.controller;

import com.oopsfeedmecode.demo.model.UserRequest;
import com.oopsfeedmecode.demo.validation.annotations.Validate;
import com.oopsfeedmecode.demo.validation.service.ValidationService;
import com.oopsfeedmecode.demo.validation.validators.AgeValidator;
import com.oopsfeedmecode.demo.validation.validators.EmailValidator;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class UserController {

    private final ValidationService validationService;

    public UserController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping("/user")
    @Validate({EmailValidator.class, AgeValidator.class})
    public Mono<ResponseEntity<String>> createUser(@Valid @RequestBody UserRequest request) {
        // Perform initial validations using @Valid and custom validators

        // Example complex logic
        return Mono.justOrEmpty(request)
                .flatMap(req -> {
                    // Simulate checking cache
                    boolean userPresentInCache = checkUserInCache(req.getEmail());
                    validationService.validateUserPresenceInCache(req.getEmail(), userPresentInCache);

                    // Additional complex logic can be added here

                    // If everything is valid, proceed with user creation
                    return Mono.just(ResponseEntity.ok("User created successfully"));
                });
    }

    private boolean checkUserInCache(String email) {
        // Simulate cache check logic
        return false; // For demonstration, assume user is not in cache
    }
}

