package com.oopsfeedmecode.demo.validation.validators;

import com.oopsfeedmecode.demo.model.UserRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component("ageValidator")
public class AgeValidator implements Validator<UserRequest> {
    @Override
    public Mono<Boolean> validate(UserRequest request) {
        return Mono.just(request.getAge() >= 19);
    }
}

