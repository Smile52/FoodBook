package com.smile.food.service;

import com.smile.food.entity.FoodInfoEntity;
import org.springframework.stereotype.Service;

@Service
public interface UploadService {
    int uploadFood(FoodInfoEntity entity);
}
