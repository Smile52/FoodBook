package com.smile.food.dao;

import com.smile.food.model.Food;

import java.util.List;

public interface FoodMapper {
    int insert(Food record);
    List<Food> findAll();
    int insertSelective(Food record);
}