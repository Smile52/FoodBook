package com.smile.food;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.smile.food.dao")
public class FoodBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodBookApplication.class, args);
    }

}

