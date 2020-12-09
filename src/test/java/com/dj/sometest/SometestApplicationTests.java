package com.dj.sometest;

import com.dj.sometest.entity.User;
import com.dj.sometest.mapper.UserMapper;
import com.dj.sometest.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
class SometestApplicationTests {

    @Autowired
    ThreadPoolExecutor executor;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    UserMapper userMapper;



    @Test
    void contextLoads() {

        System.out.println("主线程111111111111111");

        System.out.println("线程池333333333333333");
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                test2();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }, executor);

       /* future.whenComplete((re,e) -> {

            System.out.println(e);
        });*/

        System.out.println("主线程2222222222222");
    }

    @Test
    public void test2(){
        String uploadFile = "D:\\test.xls";
        String directory = "/opt";
        SftpUtil.upload(directory,uploadFile);
        System.out.println("成功");
    }

    @Test
    public void test3(){
        Long start = System.currentTimeMillis();
        Dom4jXml.createXml();
        System.out.println("运行时间："+ (System.currentTimeMillis() - start));
    }

    @Test
    public void test4(){
        Long start = System.currentTimeMillis();
        JdomXml.createXml();
        System.out.println("运行时间："+ (System.currentTimeMillis() - start));
    }

    @Test
    public void test5(){
        Long start = System.currentTimeMillis();
        JdomXml.createXml();
        System.out.println("运行时间："+ (System.currentTimeMillis() - start));
    }











}
