package com.redis.proxy.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author April Chen
 * @date 2019/3/21 20:51
 */
@SpringBootApplication
public class RedisProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisProxyApplication.class, args);
    }
}
