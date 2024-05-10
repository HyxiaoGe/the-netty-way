package com.hyxiao.netty.v2.native_byte_test;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 78976413468911L;

    private String userId;
    private Integer age;
    private String userName;
    private String[] favorite;

}
