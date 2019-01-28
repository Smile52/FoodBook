package com.smile.food.impl;

import com.smile.food.service.HelloService;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {

    public String sayHello() {
        return "1111";
    }
}
