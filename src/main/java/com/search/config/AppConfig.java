package com.search.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.search.ThirdPartyProvider.Provider1;
import com.search.ThirdPartyProvider.ThirdPartyDataProvider;
import com.search.ThirdPartyProvider.rateLimiter.ThirdPartyBucket4jRateLimiter;
import com.search.ThirdPartyProvider.rateLimiter.ThirdPartyRateLimiter;
import com.search.async.Producer;
import com.search.ratelimiter.Bucket4jRateLimiter;
import com.search.ratelimiter.RateLimiter;
import com.search.service.SearchAsyncService;
import com.search.service.SearchService;

@Configuration
public class AppConfig {
	
    
    @Bean
    public RateLimiter rateLimiter() {
        return new Bucket4jRateLimiter();
    }
    
    @Bean
    public SearchService searchService() {
        return new SearchService();
    }
    
    @Bean
    public Producer getProducer() {
    	return new Producer();
    }
    
    @Bean
    public SearchAsyncService searchAsyncService() {
        return new SearchAsyncService(rateLimiter(), getProducer());
    }
    
    @Bean
    public ThirdPartyRateLimiter thirdPartyRateLimiter() {
        return new ThirdPartyBucket4jRateLimiter();
    }
    
    
    @Bean(name =" provider1")
    public ThirdPartyDataProvider provider1() {
    	return new Provider1(thirdPartyRateLimiter());
    }
    
    @Bean(name =" provider2")
    public ThirdPartyDataProvider provider2() {
    	return new Provider1(thirdPartyRateLimiter());
    }
    
    
}
