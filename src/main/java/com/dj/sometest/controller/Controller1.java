package com.dj.sometest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Chris
 * @Date: 2020/7/29 20:29
 */
@RestController
@RequestMapping("/test1")
public class Controller1 {


    @Autowired
    ThreadPoolExecutor executor;
    @Autowired
    RedisTemplate redisTemplate;


    @GetMapping("/test")
    public String test() {
        return "hello";
    }


    @GetMapping("/test2")
    public void test2() {
        System.out.println("主线程111111111111111");
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                test3();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, executor);

        System.out.println("主线程222222222222222");


    }


    @Async
    public void test3() throws InterruptedException {

        Thread.sleep(3000);
        System.out.println("线程池333333333333333");

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("线程池4444444444444444");
            String s = null;
            boolean b = s.equals("222");
        }, executor);

        future2.whenComplete((re, e) -> {
            e.printStackTrace();
        });

        int i = 10/0;

    }


}

