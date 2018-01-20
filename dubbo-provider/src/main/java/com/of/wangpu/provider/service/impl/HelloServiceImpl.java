package com.of.wangpu.provider.service.impl;

import com.of.wangpu.api.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Created by Kenvin on 2018/1/10.
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
