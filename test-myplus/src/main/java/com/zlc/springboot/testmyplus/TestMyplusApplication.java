package com.zlc.springboot.testmyplus;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zlc.springboot.testmyplus.mapper")
public class TestMyplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestMyplusApplication.class, args);
    }

}
