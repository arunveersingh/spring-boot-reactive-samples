package com.oopsfeedmecode.demo.validation.validators;

import reactor.core.publisher.Mono;

public interface Validator<T> {
    Mono<Boolean> validate(T request);
}
