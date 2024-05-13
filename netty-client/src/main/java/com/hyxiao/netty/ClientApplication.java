package com.hyxiao.netty;


import com.hyxiao.netty.listener.ApplicationListenerReadyEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication
{
    public static void main( String[] args )
    {
        SpringApplication application = new SpringApplication(ClientApplication.class);
        application.addListeners(new ApplicationListenerReadyEvent());
        application.run(args);
    }
}
