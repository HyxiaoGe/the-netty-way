package com.hyxiao.netty.v2.marshalling;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 请求数据
 */
@Setter
@Getter
public class RequestData implements Serializable {

    private static final long serialVersionUID = 735910641123231567L;

    private String id;
    private String name;
    private String requestMessage;
    private byte[] attachment;


}
