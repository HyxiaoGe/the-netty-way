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

    }

    @Cmd(cmd = "update")
    public void updateReturn(MessageModule.ResultType resultType, byte[] data){

    }

}
