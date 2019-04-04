package com.smile.food.utils;

import com.alibaba.fastjson.JSONObject;
import com.smile.food.model.Food;

import java.util.List;

public class FoodUtils {

    public static int getFoodType(String type){
        if (type.equals("早餐")){
            return 1;

        }else if (type.equals("午餐")){
            return 2;

        }else if (type.equals("晚餐")){
            return 4;
        }else if (type.equals("甜点")){
            return 5;
        }
        return 9;
    }

    public static Food convert(Food food){
        String step = food.getStep();
        String material = food.getMaterial();

        List<Food.Step> steps= JSONObject.parseArray(step, Food.Step.class);
        List<Food.Material> materials=JSONObject.parseArray(material, Food.Material.class);
        food.setMaterials(materials);
        food.setSteps(steps);
        return food;
    }

}
