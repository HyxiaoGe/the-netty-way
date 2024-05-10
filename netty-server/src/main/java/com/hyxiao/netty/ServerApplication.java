package com.hyxiao.netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication
{
    public static void main( String[] args )
    {
        System.out.println("Netty Server Start...");
        SpringApplication application = new SpringApplication(ServerApplication.class);
        application.run(args);
    }
}
