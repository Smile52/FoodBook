package com.smile.food.controller;

import com.smile.food.impl.HelloServiceImpl;
import com.smile.food.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private HelloServiceImpl mHelloService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HashMap<String, Object> map){
        System.out.println("访问了");
        map.put("hello","Food");
        return "/index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return  mHelloService.sayHello();
    }

}
