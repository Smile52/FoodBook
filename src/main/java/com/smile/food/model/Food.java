package com.smile.food.model;

public class Food {
    private Integer id;

    private String name;

    private String decription;

    private String finshtime;

    private Integer difficulty;

    private Integer type;

    private Integer authorid;

    private String material;

    private String step;

    private String photo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription == null ? null : decription.trim();
    }

    public String getFinshtime() {
        return finshtime;
    }

    public void setFinshtime(String finshtime) {
        this.finshtime = finshtime == null ? null : finshtime.trim();
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step == null ? null : step.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    /**
     * 材料类
     */
    public static class Material{
        private String name;//名字
        private String weight;//重量

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Material{" +
                    "name='" + name + '\'' +
                    ", weight='" + weight + '\'' +
                    '}';
        }
    }

    /**
     * 制作步骤
     */
    public static class Step{
        private String des;
        private String photo;

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        @Override
        public String toString() {
            return "Step{" +
                    "des='" + des + '\'' +
                    ", photo='" + photo + '\'' +
                    '}';
        }
    }
}