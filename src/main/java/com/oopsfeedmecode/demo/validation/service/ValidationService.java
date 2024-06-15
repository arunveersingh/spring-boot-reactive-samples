package com.oopsfeedmecode.demo.validation.service;

import com.oopsfeedmecode.demo.exception.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public void validateUserPresenceInCache(String userId, boolean isPresent) {
        if (!isPresent) {
            throw new ValidationException("User not found in cache");
        }
    }

    // Additional validation methods can be added here
}

