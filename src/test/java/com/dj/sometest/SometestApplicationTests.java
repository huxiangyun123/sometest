package com.dj.sometest;

import com.dj.sometest.entity.Book;
import com.dj.sometest.util.JavaToXml;
import com.dj.sometest.util.JdomXml;
import com.dj.sometest.util.SftpUtil;
import com.dj.sometest.util.Dom4jXml;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
class SometestApplicationTests {

    @Autowired
    ThreadPoolExecutor executor;

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

    @Test
    public void test6(){
        Book b1 = new Book("冰与火之歌", "123", 39);
        List<String> list = new ArrayList<>();
        list.add("乔治");
        list.add("马丁");
        list.add("马丁 2");
        list.add("马丁 3");
        b1.setAuthors(list);
        JavaToXml.toXml(b1);
    }


}
