package com.smile.food.service;

import com.github.pagehelper.PageInfo;
import com.smile.food.model.TokenModel;
import com.smile.food.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    int addUser(User user);
    PageInfo<User> findAllUser(int pageNum, int pageSize);

    List<User> findListByPage(int currIndex, int pageSize);

    Object login(String phone, String pwd);

    int insertToken(TokenModel tokenModel);
    int updateToken(int userId, String token);

    User findUserByToken(String token);

    String findTokenByUserId(int id);
}
