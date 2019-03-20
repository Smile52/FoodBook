package com.smile.food.utils;

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
}
