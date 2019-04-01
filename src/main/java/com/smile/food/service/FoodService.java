package com.smile.food.service;

import com.smile.food.model.Food;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodService {
    int addFood(Food food);

    List<Food> findAll();
    List<Food> findListByPage(int currIndex, int pageSize);

    List<Food> findListByType(int type);

    List<Food> searchFoodByName(String name);

}
