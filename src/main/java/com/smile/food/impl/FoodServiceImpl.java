package com.smile.food.impl;

import com.smile.food.dao.FoodMapper;
import com.smile.food.model.Food;
import com.smile.food.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "foodService")
@Transactional
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodMapper mFoodMapper;

    @Override
    public int addFood(Food food) {
        return mFoodMapper.insert(food);
    }

    @Override
    public List<Food> findListByPage(int currIndex, int pageSize) {

        return null;
    }

    @Override
    public List<Food> findListByType(int type) {

        return null;
    }
}
