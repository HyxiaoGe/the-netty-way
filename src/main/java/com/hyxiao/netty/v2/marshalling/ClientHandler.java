package com.hyxiao.netty.v2.marshalling;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutorGroup;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        try {
            ResponseData responseData = (ResponseData) msg;
            System.out.println("Client : " + responseData.getId() + ", " + responseData.getName() + ", " + responseData.getResponseMessage());
        } finally {
            ReferenceCountUtil.release(msg);
        }

    }
}
