package com.hhd.netty.codec;

import com.hhd.netty.bean.LiveMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 *
 * @author hengda.hu
 * @date 2020/11/27 11:06
 */
public class LiveDecoder extends ReplayingDecoder<LiveDecoder.LiveState> {

    public enum LiveState {
        LENGTH,
        CONTENT
    }

    private LiveMessage message = new LiveMessage();

    public LiveDecoder() {
        super(LiveState.CONTENT);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        switch (state()) {
            case LENGTH:
                int length = byteBuf.readInt();
                if (length > 0) {
                    checkpoint(LiveState.CONTENT);
                } else {
                    list.add(message);
                }
                break;
            case CONTENT:
                byte[] bytes = new byte[message.getLength()];
                byteBuf.readBytes(bytes);
                String content = new String(bytes);
                message.setContent(content);
                list.add(message);
                break;
            default:
                throw new IllegalAccessException("invalid state:" + state());
        }
    }
}
