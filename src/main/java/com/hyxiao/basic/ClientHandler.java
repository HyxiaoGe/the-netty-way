package com.hyxiao.basic;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * channelActive方法会在客户端连接建立成功之后被调用
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        System.out.println(new Date() + ": 客户端写出数据！");
        //  1.获取数据
        ByteBuf buffer = getByteBuf(ctx);
        //  2.写数据
        ctx.channel().writeAndFlush(buffer);

    }

    /**
     * channelRead 方法在接收到服务端发来的数据之后被回调。
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + ": 客户端读到数据 -> " + byteBuf.toString(StandardCharsets.UTF_8));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        //  1. 获取二进制抽象 ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();
        //  2. 准备数据，指定字符串的字符串为 utf-8
        byte[] bytes = "客户端：你好， 你叫什么名字".getBytes(StandardCharsets.UTF_8);
        //  3. 填充数据到 ByteBuf
        buffer.writeBytes(bytes);

        return buffer;
    }

}
