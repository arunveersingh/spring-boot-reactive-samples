package com.oopsfeedmecode.demo.validation.aspect;

import com.oopsfeedmecode.demo.exception.ValidationException;
import com.oopsfeedmecode.demo.model.UserRequest;
import com.oopsfeedmecode.demo.validation.config.ValidationConfig;
import com.oopsfeedmecode.demo.validation.validators.Validator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Aspect
@Component
public class ValidationAspect {

    private final ApplicationContext applicationContext;
    private final ValidationConfig validationConfig;
    private final Map<String, Validator<UserRequest>> validators;

    public ValidationAspect(ApplicationContext applicationContext, ValidationConfig validationConfig, Map<String, Validator<UserRequest>> validators) {
        this.applicationContext = applicationContext;
        this.validationConfig = validationConfig;
        this.validators = validators;
    }

    @Around("@annotation(com.oopsfeedmecode.demo.validation.annotations.Validate) && args(request,..)")
    public Object validateMethod(ProceedingJoinPoint joinPoint, UserRequest request) throws Throwable {
        String apiName = joinPoint.getSignature().getName();

        List<Validator<UserRequest>> validatorsToApply = new ArrayList<>(validators.values());

        List<String> skipValidators = validationConfig.getSkipValidators().getOrDefault(apiName, new ArrayList<>());
        validatorsToApply.removeIf(validator -> skipValidators.contains(validator.getClass().getSimpleName()));

        List<String> addValidators = validationConfig.getAddValidators().getOrDefault(apiName, new ArrayList<>());
        addValidators.forEach(validatorName -> validatorsToApply.add((Validator<UserRequest>) applicationContext.getBean(validatorName)));

        List<Boolean> validationResults = validatorsToApply.stream()
                .map(validator -> validator.validate(request).block())
                .collect(Collectors.toList());

        boolean isValid = validationResults.stream().allMatch(result -> result);

        if (isValid) {
            return joinPoint.proceed();
        } else {
            throw new ValidationException("Custom validation failed");
        }
    }
}



