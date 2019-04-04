package com.smile.food.impl;

import com.alibaba.fastjson.JSONObject;
import com.smile.food.config.FoodConfig;
import com.smile.food.entity.FoodInfoEntity;
import com.smile.food.model.Food;
import com.smile.food.service.FoodService;
import com.smile.food.service.UploadService;
import com.smile.food.utils.FoodUtils;
import com.smile.food.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class UploadServiceImpl implements UploadService {

    @Autowired
    FoodService mFoodService;

    @Override
    public int uploadFood(FoodInfoEntity entity ) {

        System.out.println("name "+entity.getName());
        System.out.println("des "+entity.toString());
        MultipartFile[] files = entity.getFile_step();
        List<String> stepImgs=new ArrayList<>();
        String filePath = FoodConfig.IMAGE_PATH;

        for (int i = 0; i <files.length ; i++) {
            System.out.println("file Name "+files[i].getOriginalFilename());

            String fileName = files[i].getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

            long currentTime=System.currentTimeMillis();

            File dest = new File(filePath + currentTime+"."+suffix);
            try {
                files[i].transferTo(dest);
                stepImgs.add(currentTime+"."+suffix);
            }catch (Exception e){
                //return ResultUtils.error(99,"上传失败");
                return 0;
            }
        }
        MultipartFile photoFile=entity.getPhoto();
        String photoFileName=photoFile.getOriginalFilename();
        String suffix = photoFileName.substring(photoFileName.lastIndexOf(".") + 1);
        long currentTime=System.currentTimeMillis();
        File photoDest=new File(filePath+currentTime+"photo."+ suffix);
        String photoPath=currentTime+"photo."+ suffix;

        try {
            photoFile.transferTo(photoDest);
        }catch (Exception e){
           // return ResultUtils.error(99,"上传失败");
            return 0;
        }

        List<Food.Material> materials=new ArrayList<>();
        List<Food.Step> steps=new ArrayList<>();

        for (int i = 0; i < stepImgs.size(); i++) {
            Food.Step step=new Food.Step();
            step.setPhoto(stepImgs.get(i));
            step.setDes(entity.getDes_step()[i]);
            steps.add(step);
        }
        String[] materialArray = entity.getMaterial();
        for (int i = 0; i < materialArray.length; i++) {
            Food.Material material=new Food.Material();
            material.setName(materialArray[i]);
            material.setWeight(entity.getWeight()[i]);
            materials.add(material);
        }

        Food food=new Food();
        food.setAuthorid(1000);
        food.setDecription(entity.getDes());
        food.setDifficulty(3);
        food.setFinshtime("20分钟");
        food.setName(entity.getName());
        food.setStep(JSONObject.toJSONString(steps));
        food.setMaterial(JSONObject.toJSONString(materials));

        food.setType(FoodUtils.getFoodType(entity.getFood_type()));
        food.setPhoto(photoPath);

        return mFoodService.addFood(food);
    }
}
