package com.search.ThirdPartyProvider;

import com.search.ThirdPartyProvider.model.ThirdPartyRequest;
import com.search.ThirdPartyProvider.model.ThirdPartyResponse;
import com.search.ThirdPartyProvider.rateLimiter.ThirdPartyRateLimiter;

import io.github.bucket4j.Bucket;

public class Provider2 implements ThirdPartyDataProvider{

	
    private final ThirdPartyRateLimiter rateLimiter;
    private final static String PROVIDER_NAME = "provider1";
    
    public Provider2(ThirdPartyRateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

	public ThirdPartyResponse searchData(ThirdPartyRequest request) {
		// TODO Auto-generated method stub
		
		String clientId = request.getclientId();
		Bucket bucket = rateLimiter.getBucket(clientId, PROVIDER_NAME );
        if (bucket.tryConsume(1)) {
            // invoke for acctual provider2 client 
        	
        	System.out.println("Search results for");
        } else {
            System.out.println("Rate limit exceeded");
        }
		return null;
	}

}
