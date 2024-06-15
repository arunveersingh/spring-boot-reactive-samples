package com.oopsfeedmecode.demo.validation.annotations;

import com.oopsfeedmecode.demo.validation.validators.Validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {
    Class<? extends Validator>[] value();
}

