package com.smile.food.handle;

import com.smile.food.domain.BaseResult;
import com.smile.food.exception.FoodException;
import com.smile.food.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常捕获类
 */
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResult handle(Exception e){
        if ( e instanceof FoodException){
            FoodException foodException= (FoodException) e;
            return ResultUtils.error(foodException.getCode(), foodException.getMessage());
        } else {

            logger.error("系统异常{}", e.getMessage());
            return ResultUtils.error(99, e.getMessage());
        }
    }
}
