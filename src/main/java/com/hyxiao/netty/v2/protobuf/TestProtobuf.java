package com.hyxiao.netty.v2.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

public class TestProtobuf {


    public static byte[] serialObject2Bytes() {
        UserModule.User.Builder userBuilder = UserModule.User.newBuilder();

        userBuilder.setUserId("1001").setAge(27).setUserName("张三").addFavorite("足球").addFavorite("篮球");

        UserModule.User user = userBuilder.build();

        byte[] data = user.toByteArray();

        // [10, 4, 49, 48, 48, 49, 16, 27, 26, 6, -27, -68, -96, -28, -72, -119, 34, 6, -24, -74, -77, -25, -112, -125, 34, 6, -25, -81, -82, -25, -112, -125]
        System.err.println(Arrays.toString(data));

        return data;
    }

    public static UserModule.User serialByte2Object(byte[] data) {
        try {
            return UserModule.User.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        byte[] data = serialObject2Bytes();

        UserModule.User user = serialByte2Object(data);

        System.err.println("userId: " + user.getUserId());
        System.err.println("age: " + user.getAge());
        System.err.println("userName: " + user.getUserName());
        System.err.println("favorite: " + user.getFavoriteList());

    }

}
