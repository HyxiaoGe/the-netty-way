package com.hyxiao.netty.v2.marshalling;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        RequestData requestData = (RequestData) msg;
        System.out.println("Server : " + requestData.getId() + ", " + requestData.getName() + ", " + requestData.getRequestMessage());

        ResponseData responseData = new ResponseData();
        responseData.setId("response: " + requestData.getId());
        responseData.setName("response: " + requestData.getName());
        responseData.setResponseMessage("response: " + requestData.getRequestMessage());

        ctx.writeAndFlush(responseData);

    }
}
