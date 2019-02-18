package com.smile.food.entity;

import org.springframework.web.multipart.MultipartFile;

public class FoodInfoEntity {
    private String name;
    private String des;
    private MultipartFile[] file;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }



    public MultipartFile[] getFile() {
        return file;
    }

    public void setFile(MultipartFile[] file) {
        this.file = file;
    }
}
