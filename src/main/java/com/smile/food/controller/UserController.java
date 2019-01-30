package com.smile.food.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.smile.food.annotation.UserLoginToken;
import com.smile.food.model.User;
import com.smile.food.service.UserService;
import com.smile.food.utils.ResultUtils;
import org.attoparser.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.TextUtils;

import java.util.List;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController  {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public Object addUser(User user){
        int result =userService.addUser(user);
        if (result==1){
            return ResultUtils.success("注册成功");
        }else {
            return ResultUtils.error(99,"注册失败");
        }
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "1") int pageSize){

        PageInfo<User> allUser = userService.findAllUser(pageNum, pageSize);
        List<User> list = allUser.getList();


        return ResultUtils.success(list);

    }

    @ResponseBody
    @GetMapping("/page/{c}/{num}")
    public Object findListByPage(@PathVariable("c") Integer c, @PathVariable("num") Integer num){
        List<User> list=userService.findListByPage(c, num);
        return ResultUtils.success(list);
    }


    @ResponseBody
    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public Object login(@RequestBody JSONObject jsonObject){
        System.out.println("入参: " +jsonObject.toJSONString());
        String phone =jsonObject.getString("phone");
        String pwd=jsonObject.getString("password");
        Object o=userService.login(phone,pwd);
        if (o instanceof String){
           ResultUtils.error(99, o.toString());
        }else {
            return ResultUtils.success(o);
        }
        return jsonObject.toJSONString();
    }

    @UserLoginToken
    @ResponseBody
    @GetMapping("/info")
    public Object getUserInfo(@RequestHeader(value="token") String token){

        User user=userService.findUserByToken(token);
        user.setToken(token);
        return ResultUtils.success(user);
    }










}
