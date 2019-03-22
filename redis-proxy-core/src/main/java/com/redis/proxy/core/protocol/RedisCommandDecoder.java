package com.redis.proxy.core.protocol;

import com.redis.proxy.core.contants.RedisConsts;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author April Chen
 * @date 2019/3/22 15:00
 */
public class RedisCommandDecoder extends ReplayingDecoder<DecodeState> {

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        RedisCommand redisCommand = new RedisCommand();

        switch (state()) {
            case DECODE_SKIP:
                try {
                    skip(in);
                    checkpoint(DecodeState.DECODE_INIT);
                } finally {
                    checkpoint();
                }
            case DECODE_INIT:
                byte b = in.readByte();
                if (b == RedisConsts.ASTERISK_BYTE) {//redis命令协议开头
                    b = in.readByte();
                    in.readerIndex(in.readerIndex() - 1);
                    checkpoint(DecodeState.DECODE_ARG_COUNT);
                } else if (b == RedisConsts.PING_BYTE) {//redis-benchmark
                    in.resetReaderIndex();
                    redisCommand.setArgCount(readInt(in));
                    List<byte[]> args = new ArrayList<byte[]>(redisCommand.getArgCount());
                    args.add(RedisConsts.PING.getBytes());
                    redisCommand.setArgs(args);
                    checkpoint(DecodeState.DECODE_ARG_COUNT);
                } else {
                    throw new Exception("DECODE_INIT unexpected character, ch: " + String.valueOf(b));
                }
            case DECODE_ARG_COUNT:
                if (redisCommand != null && redisCommand.getArgCount() == 0) {
                    redisCommand.setArgCount(readInt(in));
                }
                checkpoint(DecodeState.DECODE_ARG);
            case DECODE_ARG:
                if (redisCommand.getArgs() == null || redisCommand.getArgs().size() == 0) {
                    List<byte[]> args = new ArrayList<byte[]>(redisCommand.getArgCount());
                    while (args.size() < redisCommand.getArgCount()) {
                        byte by = in.readByte();
                    }
                }
        }
    }

    private void skip(ByteBuf buf) {
        for (; ; ) {
            byte ch = buf.readByte();
            if (ch == RedisConsts.ASTERISK_BYTE || ch == RedisConsts.PING_BYTE) {
                break;
            }
        }
    }

    private int readInt(ByteBuf buf) throws Exception {
        StringBuilder sb = new StringBuilder();
        char ch = (char) buf.readByte();
        while (ch != RedisConsts.CR) {
            sb.append(ch);
            ch = (char) buf.readByte();//读取\r
        }
        buf.readByte();//读取\n
        String str = sb.toString();
        try {
            int result = 1;
            if (!RedisConsts.PING.equalsIgnoreCase(str)) {
                result = Integer.parseInt(str);
            }
            return result;
        } catch (NumberFormatException e) {
            throw new Exception("readInt unexpected result: " + str);
        }
    }
}
