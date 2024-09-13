package com.search.ratelimiter;

import io.github.bucket4j.Bucket;

public interface RateLimiter {

     Bucket getBucket(String clientId, String apiKey);
}
