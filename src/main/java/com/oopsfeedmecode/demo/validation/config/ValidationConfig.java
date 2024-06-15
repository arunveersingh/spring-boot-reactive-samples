package com.oopsfeedmecode.demo.validation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "validation")
public class ValidationConfig {
    private Map<String, List<String>> skipValidators = new HashMap<>();
    private Map<String, List<String>> addValidators = new HashMap<>();

    public Map<String, List<String>> getSkipValidators() {
        return skipValidators;
    }

    public void setSkipValidators(Map<String, List<String>> skipValidators) {
        this.skipValidators = skipValidators;
    }

    public Map<String, List<String>> getAddValidators() {
        return addValidators;
    }

    public void setAddValidators(Map<String, List<String>> addValidators) {
        this.addValidators = addValidators;
    }
}