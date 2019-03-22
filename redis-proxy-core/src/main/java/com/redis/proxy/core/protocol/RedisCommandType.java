package com.redis.proxy.core.protocol;

/**
 * @author April Chen
 * @date 2019/3/22 13:49
 */
public enum RedisCommandType {

    INLINE_COMMAND(null),
    SIMPLE_STRING((byte) '+'),
    ERROR((byte) '-'),
    INTEGER((byte) ':'),
    BULK_STRING((byte) '$'),
    ARRAY_HEADER((byte) '*');

    private final Byte value;

    RedisCommandType(Byte value) {
        this.value = value;
    }

    public RedisCommandType valueOf(byte value) {
        switch (value) {
            case '+':
                return SIMPLE_STRING;
            case '-':
                return ERROR;
            case ':':
                return INTEGER;
            case '$':
                return BULK_STRING;
            case '*':
                return ARRAY_HEADER;
            default:
                return INLINE_COMMAND;
        }
    }
}
