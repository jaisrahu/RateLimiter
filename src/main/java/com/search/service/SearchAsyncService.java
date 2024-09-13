package com.search.service;

import java.util.ArrayList;
import java.util.List;

import com.search.async.Producer;
import com.search.ratelimiter.RateLimiter;

import io.github.bucket4j.Bucket;

public class SearchAsyncService {

	 private final RateLimiter rateLimiter;
	 private final Producer producer;
	    private final static String API_KEY = "SearchService";
	    
	    public SearchAsyncService(RateLimiter rateLimiter, Producer producer) {
	        this.rateLimiter = rateLimiter;
	        this.producer = producer;
	    }
	    
	    public String performSearch(String clientId, String query) {
	        Bucket bucket = rateLimiter.getBucket(clientId, API_KEY );
	        if (bucket.tryConsume(1)) {  	
	        	// push message into queue 
	        	List<String> messageList = new ArrayList<String>(); // get it from query 
	        	for(String msg : messageList) {
		        	producer.receiveRequest(query);

	        	}
	            return "Wait for result to be ready " + query;
	        } else {
	            return "Rate limit exceeded";
	        }
	    }
}
