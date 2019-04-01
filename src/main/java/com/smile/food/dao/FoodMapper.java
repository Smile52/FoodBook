package com.smile.food.dao;

import com.smile.food.model.Food;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodMapper {
    int insert(Food record);
    List<Food> findAll();
    int insertSelective(Food record);
    List<Food> findListByType(@Param("type")Integer type);
    List<Food> searchFoodByName(@Param("foodName") String name);
}