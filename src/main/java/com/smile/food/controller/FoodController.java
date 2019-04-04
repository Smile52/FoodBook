package com.smile.food.controller;

import com.alibaba.fastjson.JSONObject;
import com.smile.food.config.FoodConfig;
import com.smile.food.dao.FoodMapper;
import com.smile.food.entity.FoodInfoEntity;
import com.smile.food.model.Food;
import com.smile.food.service.FoodService;
import com.smile.food.service.UploadService;
import com.smile.food.utils.FoodUtils;
import com.smile.food.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/food")
public class FoodController {


    @Autowired
    FoodService mFoodService;
    @Autowired
    UploadService mUploadService;

    /**
     * 多个图片和多个参数上传
     * @param entity
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public Object upload(FoodInfoEntity entity){

        if (mUploadService.uploadFood(entity)==1){
            return ResultUtils.success("添加成功");
        }else {
            return ResultUtils.error(99,"添加失败");
        }

    }

    @RequestMapping(value = "/addFood", method = RequestMethod.GET)
    public String addFood(){

        return "addFood";
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public Object findAllFood(){

        return ResultUtils.success(mFoodService.findAll());
    }

    @RequestMapping(value = "/listByType/{type}", method = RequestMethod.GET)
    @ResponseBody
    public Object findListByType(@PathVariable("type") Integer type){
        return ResultUtils.success(mFoodService.findListByType(type));
    }

    @RequestMapping(value = "/searchFood/{foodName}" , method = RequestMethod.GET)
    @ResponseBody
    public Object searchFoodByName(@PathVariable("foodName") String name){
        return ResultUtils.success(mFoodService.searchFoodByName(name));
    }


}
