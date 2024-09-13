package com.search.service;

import com.search.ThirdPartyProvider.ThirdPartyDataProvider;
import com.search.ThirdPartyProvider.ThirdPartyDataProviderFactory;
import com.search.ThirdPartyProvider.model.ThirdPartyRequest;
import com.search.ratelimiter.*;
import io.github.bucket4j.Bucket;

import org.springframework.beans.factory.annotation.Autowired;

public class SearchService {
	
	@Autowired
    private RateLimiter rateLimiter;
    private final static String API_KEY = "SearchService";
    
    
    public String performSearch(String clientId, String query) {
        Bucket bucket = rateLimiter.getBucket(clientId, API_KEY );
        if (bucket.tryConsume(1)) {
            // Perform search logic here 
        	String proiderName = "prvider1";// actually get it from query object

        	ThirdPartyDataProvider provider = ThirdPartyDataProviderFactory.getProvider(proiderName);
        	provider.searchData(new ThirdPartyRequest());
            return "Search results for: " + query;
        } else {
            return "Rate limit exceeded";
        }
    }
}
