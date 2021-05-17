package com.example.SpringProjectDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.SpringProjectDemo.dao")
public class SpringProjectDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringProjectDemoApplication.class, args);
    }

}
