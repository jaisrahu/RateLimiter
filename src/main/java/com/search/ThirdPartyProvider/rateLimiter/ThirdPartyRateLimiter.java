package com.search.ThirdPartyProvider.rateLimiter;

import io.github.bucket4j.Bucket;

public interface ThirdPartyRateLimiter {

     Bucket getBucket(String clientId, String thirdPartyKey);
}
