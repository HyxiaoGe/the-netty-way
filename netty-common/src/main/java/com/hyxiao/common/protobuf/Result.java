package com.hyxiao.common.protobuf;

import com.google.protobuf.GeneratedMessageV3;
import lombok.Data;

@Data
public class Result <T extends GeneratedMessageV3>{

    private MessageModule.ResultType resultType;

    private T content;

    public static <T extends GeneratedMessageV3> Result<T> SUCCESS() {
        Result<T> result = new Result<>();
        result.resultType = MessageModule.ResultType.SUCCESS;

        return result;
    }

    public static <T extends GeneratedMessageV3> Result<T> SUCCESS(T content) {
        Result<T> result = new Result<>();
        result.resultType = MessageModule.ResultType.SUCCESS;
        result.content = content;

        return result;
    }

    public static <T extends GeneratedMessageV3> Result<T> FAILURE() {
        Result<T> result = new Result<>();
        result.resultType = MessageModule.ResultType.FAIL;

        return result;
    }

    public static <T extends GeneratedMessageV3> Result<T> FAILURE(T content) {
        Result<T> result = new Result<>();
        result.resultType = MessageModule.ResultType.FAIL;
        result.content = content;

        return result;
    }


}
