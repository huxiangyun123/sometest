package com.dj.sometest;


import com.dj.sometest.annotation.MyAnnotation;
import com.dj.sometest.entity.MyClassPathDefinitonScanner;
import com.dj.sometest.entity.User;
import com.dj.sometest.mapper.UserMapper;
import com.dj.sometest.util.JdomXml;
import com.dj.sometest.util.SftpUtil;
import com.dj.sometest.util.Dom4jXml;
import com.dj.sometest.util.StringUtil;
import jdk.internal.org.objectweb.asm.ClassReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

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
    public void test2() {
        String uploadFile = "D:\\test.xls";
        String directory = "/opt";
        SftpUtil.upload(directory, uploadFile);
        System.out.println("成功");
    }

    @Test
    public void test3() {
        Long start = System.currentTimeMillis();
        Dom4jXml.createXml();
        System.out.println("运行时间：" + (System.currentTimeMillis() - start));
    }

    @Test
    public void test4() {
        Long start = System.currentTimeMillis();
        JdomXml.createXml();
        System.out.println("运行时间：" + (System.currentTimeMillis() - start));
    }

    @Test
    public void test5() {
        Long start = System.currentTimeMillis();
        JdomXml.createXml();
        System.out.println("运行时间：" + (System.currentTimeMillis() - start));
    }

    @Test
    public void test6() {
        List<User> list = new ArrayList<>();
        for(int i =1;i<=10;i++){
           User user = new User();
           user.setId(i);
           user.setUsername("hh");
           list.add(user);
       }



        List<User> collect = list.stream().filter(t -> t.getId() == 20).collect(Collectors.toList());
        if(collect.size() >1){
            System.out.println("111");
        }


    }



    @Test
    public void test8() {

        String Scan_Path = "com.dj.sometest";
        GenericApplicationContext context = new GenericApplicationContext();
        MyClassPathDefinitonScanner scanner = new MyClassPathDefinitonScanner(context, MyAnnotation.class);
        // 注册过滤器
        scanner.registerTypeFilter();
        int beanCount = scanner.scan(Scan_Path);
        context.refresh();
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(beanCount);
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

    }

    @Test
    public void test9() {



    }


}
