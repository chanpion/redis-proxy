package com.redis.proxy.core.protocol;

/**
 * @author April Chen
 * @date 2019/3/22 15:04
 */
public enum DecodeState {
    DECODE_SKIP,//如果第一个字符不是*则skip直到遇到*
    DECODE_INIT,
    DECODE_ARG_COUNT,//参数数量
    DECODE_ARG,//参数
    DECODE_END//结束
}
