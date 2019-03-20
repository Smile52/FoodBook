package com.smile.food.entity;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

public class FoodInfoEntity {
    private String name;
    private String des;
    private String type;
    private MultipartFile photo;

    private MultipartFile[] file_step;
    private String[] des_step;
    private String food_type;
    private String[] material;
    private String[] weight;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public MultipartFile[] getFile_step() {
        return file_step;
    }

    public void setFile_step(MultipartFile[] file_step) {
        this.file_step = file_step;
    }

    public String[] getDes_step() {
        return des_step;
    }

    public void setDes_step(String[] des_step) {
        this.des_step = des_step;
    }

    public String getFood_type() {
        return food_type;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public String[] getMaterial() {
        return material;
    }

    public void setMaterial(String[] material) {
        this.material = material;
    }

    public String[] getWeight() {
        return weight;
    }

    public void setWeight(String[] weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "FoodInfoEntity{" +
                "name='" + name + '\'' +
                ", des='" + des + '\'' +
                ", type='" + type + '\'' +
                ", photo=" + photo +
                ", file_step=" + Arrays.toString(file_step) +
                ", des_step=" + Arrays.toString(des_step) +
                ", food_type='" + food_type + '\'' +
                ", material=" + Arrays.toString(material) +
                ", weight=" + Arrays.toString(weight) +
                '}';
    }
}
