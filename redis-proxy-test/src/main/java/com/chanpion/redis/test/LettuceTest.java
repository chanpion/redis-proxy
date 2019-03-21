package com.chanpion.redis.test;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;

public class LettuceTest {

    public static void main(String[] args) {
        RedisURI redisURI = RedisURI.create("redis-sentinel://127.0.0.1:6379,127.0.0.1:6380?sentinelMasterId=mymaster");
        RedisClient redisClient = RedisClient.create(redisURI);

        redisClient.connect();
    }
}
