package com.angels.andrea_gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiterConfig {

    @Bean
    public RedisRateLimiter redisRateLimiter() {

        return new RedisRateLimiter(
                10,   // replenishRate
                20    // burstCapacity
        );
    }

}