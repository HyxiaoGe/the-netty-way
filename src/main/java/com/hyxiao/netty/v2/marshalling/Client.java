package com.hyxiao.netty.v2.marshalling;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup wGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(wGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture future = bootstrap.connect("127.0.0.1", 8765).sync();

        for (int i = 0; i < 10; i++) {
            RequestData requestData = new RequestData();
            requestData.setId("" + i);
            requestData.setName("pro" + i);
            requestData.setRequestMessage("数据信息" + i);
            future.channel().writeAndFlush(requestData);
        }

        future.channel().closeFuture().sync();

        wGroup.shutdownGracefully();

    }

}
