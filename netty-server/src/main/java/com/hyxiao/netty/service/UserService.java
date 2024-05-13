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

    @Cmd(cmd = "save")
    public Result<?> saveUser(byte[] data) {
        UserModule.User user;
        try {
            user = UserModule.User.parseFrom(data);
            System.err.println("ID：" + user.getUserId() + ", Name：" + user.getUserName());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return Result.FAILURE();
        }
        return Result.SUCCESS(user);
    }

    @Cmd(cmd = "update")
    public Result<?> updateUser(byte[] data) {
        UserModule.User user;
        try {
            user = UserModule.User.parseFrom(data);
            System.err.println("ID：" + user.getUserId() + ", Name：" + user.getUserName());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return Result.FAILURE();
        }
        return Result.SUCCESS(user);
    }
}
