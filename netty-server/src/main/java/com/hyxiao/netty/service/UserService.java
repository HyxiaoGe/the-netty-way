package com.hyxiao.netty.service;

import com.google.protobuf.InvalidProtocolBufferException;
import com.hyxiao.annotation.Cmd;
import com.hyxiao.annotation.Module;
import com.hyxiao.common.protobuf.Result;
import com.hyxiao.common.protobuf.UserModule;
import org.springframework.stereotype.Service;

@Service
@Module(module = "user")
public class UserService {

    @Cmd(cmd = "saveUser")
    public Result<?> saveUser(byte[] data) {
        try {
            UserModule.User user = UserModule.User.parseFrom(data);
            System.err.println(user.getUserId());
            System.err.println(user.getUserName());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return Result.FAILURE();
        }
        return Result.SUCCESS();
    }

    @Cmd(cmd = "getUser")
    public Result<?> getUser(Object user) {
        return null;
    }

    @Cmd(cmd = "updateUser")
    public Result<?> updateUser(byte[] data) {
        try {
            UserModule.User user = UserModule.User.parseFrom(data);
            System.err.println(user.getUserId());
            System.err.println(user.getUserName());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return Result.FAILURE();
        }
        return Result.SUCCESS();
    }

    @Cmd(cmd = "deleteUser")
    public Result<?> deleteUser(Object user) {
        return null;
    }

}
