package com.hyxiao.netty.service;

import com.hyxiao.annotation.Cmd;
import com.hyxiao.annotation.Module;
import com.hyxiao.common.protobuf.MessageModule;
import org.springframework.stereotype.Service;

@Service
@Module(module = "user")
public class UserReturnService {

    @Cmd(cmd = "save")
    public void saveReturn(MessageModule.ResultType resultType, byte[] data){
        if (resultType == MessageModule.ResultType.SUCCESS) {
            System.out.println("save success");
        } else {
            System.out.println("save fail");
        }
    }

    @Cmd(cmd = "update")
    public void updateReturn(MessageModule.ResultType resultType, byte[] data){
        if (resultType == MessageModule.ResultType.SUCCESS) {
            System.out.println("update success");
        } else {
            System.out.println("update fail");
        }
    }

}
