package com.hyxiao.netty.handler;

import com.hyxiao.common.protobuf.MessageModule;
import com.hyxiao.execute.RequestMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    ThreadPoolExecutor workerThreadPool = new ThreadPoolExecutor(
            5, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), new ThreadPoolExecutor.DiscardOldestPolicy());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        MessageModule.Message requestMsg = (MessageModule.Message) msg;
        workerThreadPool.submit(new RequestMsg(requestMsg, ctx));

    }
}
