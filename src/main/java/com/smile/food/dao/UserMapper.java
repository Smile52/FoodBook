package com.smile.food.dao;

import com.smile.food.model.TokenModel;
import com.smile.food.model.User;
import jdk.nashorn.internal.parser.Token;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    List<User> selectUser();

    List<User> findListByPage(@Param("currIndex") Integer currIndex, @Param("pageSize") Integer pageSize);

    int insertToken(TokenModel token);
    int updateToken(@Param("user_id") Integer user_id, @Param("token")String token );

    User findPwdByPhone(@Param("phone") String phone);
    User findUserById(@Param("userId") Integer id);

    String findTokenByUserId(@Param("userId") Integer id);

    Integer findTokenIdByUserId(@Param("userId") Integer id);

}