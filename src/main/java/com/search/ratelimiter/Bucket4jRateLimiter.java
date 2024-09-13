package com.search.ratelimiter;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import io.github.bucket4j.Bandwidth;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class Bucket4jRateLimiter implements RateLimiter {

    private final Map<String, Bucket> clientBuckets = new ConcurrentHashMap<>();
    private final Properties properties = new Properties();

    public Bucket4jRateLimiter() {
        loadConfigurations();
    }

    private void loadConfigurations() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("api-rate-limiting.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find api-rate-limiting.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration", e);
        }
        
    }

    public Bucket getBucket(String clientId, String apiKey) {
        return clientBuckets.computeIfAbsent(apiKey+clientId, id -> createBucketForClient(id, apiKey));
    }

    private Bucket createBucketForClient(String clientId,  String apiKey) {
        String limitKey = apiKey + "." + clientId + ".limit";
        String refillIntervalKey = apiKey + "." + clientId + ".refillInterval";

        int limit = Integer.parseInt(properties.getProperty(limitKey, "100")); 
        Duration refillInterval = Duration.parse(properties.getProperty(refillIntervalKey, "PT1M")); 

        Bandwidth limitConfig = Bandwidth.classic(limit, Refill.intervally(limit, refillInterval));
        return Bucket.builder()
                .addLimit(limitConfig)
                .build();
    }
}
