package com.hyxiao.netty.listener;

import com.hyxiao.netty.client.Client;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationListenerReadyEvent implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        System.out.println("Netty Client Start...");
        Client.getInstance().init();

    }
}
