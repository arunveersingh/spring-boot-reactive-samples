package com.oopsfeedmecode.demo.validation.validators;

import com.oopsfeedmecode.demo.model.UserRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component("emailValidator")
public class EmailValidator implements Validator<UserRequest> {
    @Override
    public Mono<Boolean> validate(UserRequest request) {
        return Mono.just(request.getEmail() != null && request.getEmail().contains("@"));
    }
}

