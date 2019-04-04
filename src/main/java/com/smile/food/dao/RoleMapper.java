package com.smile.food.dao;

import com.smile.food.model.Role;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);

    Role findRoleById(@Param("roleId")Integer id);
}