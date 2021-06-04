package com.deepsingh44.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GreetingService {
	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingService.class);

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    private RestTemplate restTemplate = new RestTemplate();

    public String getMessage() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        String url = "http://localhost:7777/hello";

        return circuitBreaker.run(() -> restTemplate.getForObject(url, String.class), throwable -> getDefaultMessage(throwable));
    }

    private String getDefaultMessage(Throwable t) {
           return "Please try after some time " + t;
    }
}
