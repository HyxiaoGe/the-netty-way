package com.hyxiao.execute;

import com.hyxiao.common.protobuf.MessageBuilder;
import com.hyxiao.common.protobuf.MessageModule;
import com.hyxiao.common.protobuf.Result;
import com.hyxiao.scanner.Invoker;
import com.hyxiao.scanner.InvokerTable;
import io.netty.channel.ChannelHandlerContext;

public class RequestMsg implements Runnable{

    private MessageModule.Message message;
    private ChannelHandlerContext context;

    public RequestMsg(MessageModule.Message message, ChannelHandlerContext context) {
        this.message = message;
        this.context = context;
    }


    @Override
    public void run() {

        String module = message.getModule();
        String cmd = message.getCmd();
        byte[] data = message.getBody().toByteArray();
        Invoker invoker = InvokerTable.getInvoker(module, cmd);

        Result<?> result = (Result<?>) invoker.invoke(data);
        context.writeAndFlush(MessageBuilder.getResponseMessage(module, cmd, result.getResultType(), result.getContent()));
    }
}
