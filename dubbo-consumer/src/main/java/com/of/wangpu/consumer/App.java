package com.of.wangpu.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Kenvin on 2018/1/10.
 */
@SpringBootApplication
@ImportResource({"classpath:spring-dubbo.xml"})
public class App {

    public static void main(String[] args){
        SpringApplication.run(App.class);
    }
}
