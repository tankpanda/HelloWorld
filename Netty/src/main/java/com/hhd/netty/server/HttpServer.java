package com.hhd.netty.server;

import com.hhd.netty.codec.LiveDecoder;
import com.hhd.netty.codec.Short2ByteEncoder;
import com.hhd.netty.codec.ToIntegerDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 *
 * @author hengda.hu
 * @date 2020/11/25 17:45
 */
public class HttpServer {
    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
//        if (args.length != 1) {
//            System.err.println("Usage: " + HttpServer.class.getSimpleName() + " <port>");
//            return;
//        }
        int port = Integer.parseInt("8080");
        new HttpServer(port).start();
    }

    public void start() throws Exception {
        ServerBootstrap sb = new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        sb.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        System.out.println("init channel ch:" + socketChannel);
                        socketChannel.pipeline()
//                                .addLast("decoder", new HttpRequestDecoder())
                                .addLast("decoder", new ToIntegerDecoder())
//                                .addLast("decoder", new LiveDecoder())
//                                .addLast("encoder", new HttpResponseEncoder())
                                .addLast("encoder", new Short2ByteEncoder())
                                .addLast("aggregator", new HttpObjectAggregator(512 * 1024))
                                .addLast("handler", new HttpHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);
        sb.bind(port).sync();
    }
}
