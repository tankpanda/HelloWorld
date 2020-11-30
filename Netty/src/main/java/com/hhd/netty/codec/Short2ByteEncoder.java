package com.hhd.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 *
 * @author hengda.hu
 * @date 2020/11/27 11:02
 */
public class Short2ByteEncoder extends MessageToByteEncoder<Short> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Short aShort, ByteBuf byteBuf) throws Exception {
        byteBuf.writeShort(aShort);
    }
}
