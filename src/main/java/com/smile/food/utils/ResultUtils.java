package com.smile.food.utils;

import com.smile.food.domain.BaseResult;

public class ResultUtils {
    public static final BaseResult success(Object o){
        BaseResult result = new BaseResult();
        result.setCode(1);
        result.setMessage("Success");

        result.setData(o);
        return result;
    }

    public static final BaseResult error(Integer code, String msg){
        BaseResult result=new BaseResult();
        result.setMessage(msg);
        result.setCode(code);
        return result;
    }
}
