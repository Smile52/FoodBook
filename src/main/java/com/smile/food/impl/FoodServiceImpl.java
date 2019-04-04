package com.smile.food.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.smile.food.dao.FoodMapper;
import com.smile.food.model.Food;
import com.smile.food.service.FoodService;
import com.smile.food.utils.FoodUtils;
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
    public List<Food> findAll() {
        List<Food> all = mFoodMapper.findAll();
        for (Food food : all) {
            /*String step = food.getStep();
            String material = food.getMaterial();

            List<Food.Step> steps= JSONObject.parseArray(step, Food.Step.class);
            List<Food.Material> materials=JSONObject.parseArray(material, Food.Material.class);
            food.setMaterials(materials);
            food.setSteps(steps);*/
            food= FoodUtils.convert(food);
        }

        return mFoodMapper.findAll();
    }

    @Override
    public List<Food> findListByPage(int currIndex, int pageSize) {

        return null;
    }

    @Override
    public List<Food> findListByType(int type) {

        return mFoodMapper.findListByType(type);
    }

    @Override
    public List<Food> searchFoodByName(String name) {
        return mFoodMapper.searchFoodByName(name);
    }
}
