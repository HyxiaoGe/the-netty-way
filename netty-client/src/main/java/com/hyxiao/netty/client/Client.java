package com.hyxiao.netty.client;

import com.hyxiao.netty.config.ClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class Client {

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    private EventLoopGroup group = new NioEventLoopGroup(2);

    public static final String HOST = "127.0.0.1";

    public static final int PORT = 8765;

    private Channel channel;

    public Client() throws Exception {
        this.connect(HOST, PORT);
    }

    private void connect(String host, int port) throws Exception {

        try {
            // 配置客户端NIO线程组
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ClientInitializer());
            // 发起异步连接操作
            ChannelFuture future = bootstrap.connect(host, port).sync();
            this.channel = future.channel();
            System.out.println("Netty client start ok : " + host + ":" + port);
            this.channel.closeFuture().sync();
        } finally {
            //  所有资源释放完成之后，清空资源，再次发起重连操作
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    try {
                        connect(HOST, PORT);    // 发起重连操作
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

    }


}
