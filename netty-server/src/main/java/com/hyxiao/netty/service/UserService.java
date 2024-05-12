package com.hyxiao.netty.service;

import com.hyxiao.annotation.Cmd;
import com.hyxiao.annotation.Module;
import org.springframework.stereotype.Service;

@Service
@Module(module = "user")
public class UserService {

    @Cmd(cmd = "saveUser")
    public Object saveUser(Object user) {
        return user;
    }

    @Cmd(cmd = "getUser")
    public Object getUser(Object user) {
        return user;
    }

    @Cmd(cmd = "updateUser")
    public Object updateUser(Object user) {
        return user;
    }

    @Cmd(cmd = "deleteUser")
    public Object deleteUser(Object user) {
        return user;
    }

}
