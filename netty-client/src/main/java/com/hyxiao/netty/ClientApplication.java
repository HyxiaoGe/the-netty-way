package com.hyxiao.netty;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication
{
    public static void main( String[] args )
    {
        System.out.println("Netty Client Start...");
        SpringApplication application = new SpringApplication(ClientApplication.class);
        application.run(args);
    }
}
