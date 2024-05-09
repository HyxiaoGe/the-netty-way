package com.hyxiao.netty.v1.basic;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    private final static int MAX_RETRY = 5;

    /**
     * 失败重连
     * @param bootstrap
     * @param host
     * @param port
     * @param retry
     */
    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else if (retry == 0) {
                System.err.println(new Date() + ": 重连次数已用完");
            } else {
                //  计算第几次重连
                int order = (MAX_RETRY - retry) + 1;
                //  本次重连的间隔(每隔 1 秒、2 秒、4 秒、8 秒，以 2 的幂次来建立连接)
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败, 第" + order + "次重连......");
                //  bootstrap.config().group() 返回的就是我们在一开始的时候配置的线程模型 workerGroup
                //  workerGroup 的 schedule 方法即可实现定时任务逻辑
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    public static void main(String[] args) {

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup) // 指定线程模型
                .channel(NioSocketChannel.class)    //  指定IO类型为NIO
                //  IO处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //  负责向服务端写数据
                        socketChannel.pipeline().addLast("handler", new ClientHandler());
                    }
                });

        connect(bootstrap, "localhost", 8000, 5);
    }

}
