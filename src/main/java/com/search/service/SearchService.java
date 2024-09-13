package com.search.service;

import com.search.ratelimiter.*;
import io.github.bucket4j.Bucket;

import org.springframework.beans.factory.annotation.Autowired;

public class SearchService {
	
    private final RateLimiter rateLimiter;
    private final static String API_KEY = "SearchService";
    
    @Autowired
    public SearchService(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }
    
    public String performSearch(String clientId, String query) {
        Bucket bucket = rateLimiter.getBucket(clientId, API_KEY );
        if (bucket.tryConsume(1)) {
            // Perform search logic here 

            return "Search results for: " + query;
        } else {
            return "Rate limit exceeded";
        }
    }
}
