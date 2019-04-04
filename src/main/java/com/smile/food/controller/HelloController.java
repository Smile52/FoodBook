package com.smile.food.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.smile.food.annotation.RoleAnnotation;
import com.smile.food.impl.HelloServiceImpl;
import com.smile.food.service.HelloService;
import com.smile.food.utils.JwtUtils;
import com.smile.food.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private HelloServiceImpl mHelloService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HashMap<String, Object> map){
        System.out.println("访问了");
        map.put("hello","ff");
        String token ="";
        Date date=new Date(System.currentTimeMillis()+ JwtUtils.EXPIRE_TIME);
        Algorithm algorithm=Algorithm.HMAC256(JwtUtils.TOKEN_SECRET);
        token= JWT.create().withClaim("userName","张三").withClaim("userId","100001")
                .withExpiresAt(date)
                .sign(algorithm);
        return "index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return  mHelloService.sayHello();
    }

    @RequestMapping("/toApp")
    public String toApp(){
        return "toApp";
    }

    @RoleAnnotation(leave = 2)
    @RequestMapping("/role")
    @ResponseBody
    public Object role(){
        return ResultUtils.success("通过");
    }


}
