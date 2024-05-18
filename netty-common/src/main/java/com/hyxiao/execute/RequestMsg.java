package com.hyxiao.execute;

import com.hyxiao.common.protobuf.MessageBuilder;
import com.hyxiao.common.protobuf.MessageModule;
import com.hyxiao.common.protobuf.Result;
import com.hyxiao.scanner.Invoker;
import com.hyxiao.scanner.InvokerTable;
import io.netty.channel.ChannelHandlerContext;

public class RequestMsg implements Runnable {
    private MessageModule.Message message;

    private ChannelHandlerContext ctx;

    private final static String RETURN = "-return";

    public RequestMsg(MessageModule.Message message, ChannelHandlerContext ctx) {
        this.message = message;
        this.ctx = ctx;
    }

    @Override
    public void run() {

        String module = message.getModule();
        String cmd = message.getCmd();
        byte[] data = message.getBody().toByteArray();
        Invoker invoker = InvokerTable.getInvoker(module, cmd);
        Result<?> result = (Result<?>) invoker.invoke(data);

        ctx.writeAndFlush(MessageBuilder
                .getResponseMessage(module + RETURN,
                        cmd + RETURN,
                        result.getResultType(),
                        result.getContent()));
    }

}
