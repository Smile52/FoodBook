package com.smile.food.exception;

import com.smile.food.enums.ResultEnums;

public class FoodException extends RuntimeException {
        private Integer code;

        public FoodException (ResultEnums resultEnums){
            super(resultEnums.getMsg());
            this.code= resultEnums.getCode();

        }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
