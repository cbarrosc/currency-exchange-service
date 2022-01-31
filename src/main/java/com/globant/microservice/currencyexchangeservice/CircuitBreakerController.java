package com.globant.microservice.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircuitBreakerController {


    @GetMapping("/sample-api")
//    @Retry(name="sample-api", fallbackMethod = "hardcodedResponse")
//    @CircuitBreaker(name="default", fallbackMethod = "hardcodedResponse")
//    @RateLimiter(name="default")
    @Bulkhead(name="sample-api")
    public String sampleApi(){
        log.info("Sample Api call received");
       /* ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080-some-dummy-url", String.class);
        return forEntity.getBody();*/
        return "sample-api";
    }

    public String hardcodedResponse(Exception ex){
        return "fallback-response";
    }

}
