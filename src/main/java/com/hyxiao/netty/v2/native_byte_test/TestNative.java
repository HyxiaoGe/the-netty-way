package com.hyxiao.netty.v2.native_byte_test;

import java.io.*;
import java.util.Arrays;

public class TestNative {


    public static byte[] object2Byte() throws IOException {

        User user = new User();
        user.setUserId("1001");
        user.setAge(27);
        user.setUserName("张三");
        user.setFavorite(new String[]{"足球", "篮球"});

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();

            byte[] data = byteArrayOutputStream.toByteArray();

            System.err.println(Arrays.toString(data));

            return data;
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static User byte2Object(byte[] data) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (User) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            byteArrayInputStream.close();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

        byte[] data = object2Byte();
        User user = byte2Object(data);

        System.err.println("userId: " + user.getUserId());
        System.err.println("age: " + user.getAge());
        System.err.println("userName: " + user.getUserName());
        System.err.println("favorite: " + Arrays.toString(user.getFavorite()));

    }

}
