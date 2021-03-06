package com.dj.sometest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAspectJAutoProxy
@EnableAsync
@MapperScan("com.dj.sometest.mapper")
@SpringBootApplication
public class SometestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SometestApplication.class, args);
    }

}
