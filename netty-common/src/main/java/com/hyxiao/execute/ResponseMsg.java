package com.hyxiao.execute;

import com.hyxiao.common.protobuf.MessageBuilder;
import com.hyxiao.common.protobuf.MessageModule;
import com.hyxiao.common.protobuf.Result;
import com.hyxiao.scanner.Invoker;
import com.hyxiao.scanner.InvokerTable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ResponseMsg implements Runnable{

    private MessageModule.Message message;
    private ChannelHandlerContext context;


    public ResponseMsg(MessageModule.Message message, ChannelHandlerContext context) {
        this.message = message;
        this.context = context;
    }


    @Override
    public void run() {

        try {
            String module = message.getModule();
            String cmd = message.getCmd();
            MessageModule.ResultType resultType = this.message.getResultType();
            byte[] data = message.getBody().toByteArray();

            Invoker invoker = InvokerTable.getInvoker(module, cmd);

            invoker.invoke(resultType, data);
        } finally {
            ReferenceCountUtil.release(message);
        }
    }
}
