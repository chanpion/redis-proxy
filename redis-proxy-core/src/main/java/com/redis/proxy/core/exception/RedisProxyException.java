package com.redis.proxy.core.exception;

/**
 * @author April Chen
 * @date 2019/3/22 14:02
 */
public class RedisProxyException extends RuntimeException {

    public RedisProxyException() {
        super();
    }

    public RedisProxyException(String message) {
        super(message);
    }
}
