package com.redis.proxy.core.protocol;

import java.util.List;

/**
 * @author April Chen
 * @date 2019/3/22 14:01
 */
public class RedisCommand {
    private int argCount;
    private List<byte[]> args;

    public int getArgCount() {
        return argCount;
    }

    public void setArgCount(int argCount) {
        this.argCount = argCount;
    }

    public List<byte[]> getArgs() {
        return args;
    }

    public void setArgs(List<byte[]> args) {
        this.args = args;
    }
}
