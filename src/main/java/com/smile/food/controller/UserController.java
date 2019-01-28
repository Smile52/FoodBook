package com.smile.food.controller;


import com.github.pagehelper.PageInfo;
import com.smile.food.model.User;
import com.smile.food.service.UserService;
import com.smile.food.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/user")

public class UserController  {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(User user){


        return userService.addUser(user);
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
}
