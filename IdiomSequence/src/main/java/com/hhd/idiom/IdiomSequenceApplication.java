package com.hhd.idiom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.hhd.idiom.mapper"})
public class IdiomSequenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdiomSequenceApplication.class, args);
    }

}
