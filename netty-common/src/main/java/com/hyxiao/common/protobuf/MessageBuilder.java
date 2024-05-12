package com.hyxiao.common.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageV3;

/**
 * 创建请求和回送响应的工具类
 */
public class MessageBuilder {

    private static final int CRC_CODE = 0xabef0101;

    public static MessageModule.Message getRequestMessage(String module, String cmd, GeneratedMessageV3 data) {

        return MessageModule.Message.newBuilder()
                .setCrcCode(CRC_CODE)
                .setMessageType(MessageModule.MessageType.REQUEST)
                .setModule(module)
                .setCmd(cmd)
                .setBody(ByteString.copyFrom(data.toByteArray()))
                .build();
    }

    public static MessageModule.Message getResponseMessage(String module, String cmd, MessageModule.ResultType resultType, GeneratedMessageV3 data) {

        return MessageModule.Message.newBuilder()
                .setCrcCode(CRC_CODE)
                .setMessageType(MessageModule.MessageType.RESPONSE)
                .setModule(module)
                .setCmd(cmd)
                .setResultType(resultType)
                .setBody(ByteString.copyFrom(data.toByteArray()))
                .build();
    }

}
