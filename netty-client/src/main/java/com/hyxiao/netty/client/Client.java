package com.hyxiao.netty.client;

import com.google.protobuf.GeneratedMessageV3;
import com.hyxiao.common.protobuf.MessageBuilder;
import com.hyxiao.netty.config.ClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    private EventLoopGroup group = new NioEventLoopGroup(2);

    public static final String HOST = "127.0.0.1";

    public static final int PORT = 8765;

    private Channel channel;

    private AtomicBoolean isConnect = new AtomicBoolean(false);

    private static class SingletonHolder {
        private static final Client INSTANCE = new Client();
    }

    public static final Client getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private Client()  {
    }

    public synchronized void init() {
        if(!isConnect.get()) {
            try {
                this.connect(HOST, PORT);
                isConnect.set(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void connect(String host, int port) throws Exception {

        // 配置客户端NIO线程组
        try {
            // 配置客户端NIO线程组
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ClientInitializer());
            // 发起异步连接操作
            ChannelFuture future = bootstrap.connect(host, port).sync();
            this.channel = future.channel();
            System.out.println("Client Start.. ");
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

    /**
     * 	$sendMessage
     * 发送数据的方法
     * @param module 模块
     * @param cmd 指令
     * @param messageData 数据内容
     */
    public void sendMessage(String module, String cmd , GeneratedMessageV3 messageData) {
        this.channel.writeAndFlush(MessageBuilder.getRequestMessage(module, cmd, messageData));
    }


}
