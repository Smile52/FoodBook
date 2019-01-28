package com.smile.food.enums;

public enum ResultEnums {
    UNKNOW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功");

    private Integer code;
    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
