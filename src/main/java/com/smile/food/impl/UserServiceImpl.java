package com.smile.food.impl;

import com.alibaba.druid.util.StringUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smile.food.dao.UserMapper;
import com.smile.food.enums.ResultEnums;
import com.smile.food.exception.FoodException;
import com.smile.food.model.TokenModel;
import com.smile.food.model.User;
import com.smile.food.service.UserService;
import com.smile.food.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public PageInfo<User> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList=userMapper.selectUser();
        PageInfo result=new PageInfo(userList);

        return result;
    }

    @Override
    public List<User> findListByPage(int currPage, int pageSize) {
        int currIndex=(currPage-1)*pageSize;

        return userMapper.findListByPage(currIndex, pageSize);
    }

    @Override
    public Object login(String phone, String pwd) {
         User user=userMapper.findPwdByPhone(phone);

        if (user.getPassword().equals(pwd)){
            Date date=new Date(System.currentTimeMillis()+ JwtUtils.EXPIRE_TIME);
            Algorithm algorithm=Algorithm.HMAC256(JwtUtils.TOKEN_SECRET);

            String token= JWT.create().withClaim("userId",user.getUser_id())
                    .withExpiresAt(date)
                    .sign(algorithm);
            user.setToken(token);

            int i=userMapper.updateToken(user.getUser_id(), token);
            if (i<1){
                TokenModel model=new TokenModel();
                model.setToken(token);
                model.setUser_id(user.getUser_id());

                int r=userMapper.insertToken(model);
                if (r==1){
                    return user;
                }
            }else {
                return user;
            }
        }else

            return "账号或者密码错误";

        return "";
    }




    @Override
    public int insertToken(TokenModel tokenModel) {

        return userMapper.insertToken(tokenModel);
    }

    @Override
    public int updateToken(int userId, String token) {
        return userMapper.updateToken(userId, token);
    }

    @Override
    public User findUserByToken(String token) {
        int s;
        try {

            Map<String, Claim> claims = JwtUtils.verifyToken(token);

            Claim userId = claims.get("userId");
            s=userId.asInt();
            if (null == userId || s==0) {
                // token 校验失败, 抛出Token验证非法异常
                throw new FoodException( ResultEnums.UNKNOW_ERROR);
            }
        }catch (Exception e){
            throw new FoodException(ResultEnums.TOKEN_ERROR);
        }


        return userMapper.findUserById(s);


    }

    @Override
    public String findTokenByUserId(int id) {

        return userMapper.findTokenByUserId(id);
    }


}
