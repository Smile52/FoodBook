package com.smile.food.service;

import com.github.pagehelper.PageInfo;
import com.smile.food.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    int addUser(User user);
    PageInfo<User> findAllUser(int pageNum, int pageSize);

}
