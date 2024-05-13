package com.hyxiao.execute;

import com.hyxiao.common.protobuf.MessageModule;
import com.hyxiao.common.protobuf.MessageModule.ResultType;
import com.hyxiao.scanner.Invoker;
import com.hyxiao.scanner.InvokerTable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ResponseMsg implements Runnable {

    private MessageModule.Message message;
    private ChannelHandlerContext ctx;

    public ResponseMsg(MessageModule.Message message, ChannelHandlerContext ctx) {
        this.message = message;
        this.ctx = ctx;
    }

    @Override
    public void run() {
        try {
            //	user-return
            String module = this.message.getModule();
            //	save-return
            String cmd = this.message.getCmd();
            //	响应的结果
            ResultType resultType = this.message.getResultType();
            //	响应的内容
            byte[] data = this.message.getBody().toByteArray();

            Invoker invoker = InvokerTable.getInvoker(module, cmd);
            invoker.invoke(resultType, data);

        } finally {
            ReferenceCountUtil.release(message);
        }
    }

}
