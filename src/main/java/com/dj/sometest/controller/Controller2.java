package com.dj.sometest.controller;

import com.dj.sometest.entity.User;
import com.dj.sometest.util.JsonUtil;
import com.dj.sometest.util.SftpUtil;
import com.google.common.collect.Lists;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Chris
 * @Date: 2020/8/8 14:34
 */
@RestController
@RequestMapping("/test2")
public class Controller2 {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedissonClient redissonClient;


    @GetMapping("/test")
    public void test(HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("");
        User user = new User();
        user.setAge(10);
        user.setHabbit("篮球");
        user.setName("大侠");
        String filePath = JsonUtil.createJSONFile(user, "test", path);
        String directory = "/opt";
        SftpUtil.upload(directory,filePath);
        System.out.println("成功");
    }






    @GetMapping("/test2")
    public void test2(){
        redisTemplate.opsForValue().set("history",0);
        for(int i =0;i<10;i++){
           new Thread(() -> {
               for (int j =0;j<1000;j++){
                   RLock lock = redissonClient.getLock("lock");
                   lock.lock();
                   Integer value = (Integer) redisTemplate.opsForValue().get("history");
                   value++;
                   redisTemplate.opsForValue().set("history",value);
                   lock.unlock();
                   System.out.println("解锁===========>  "+ value);               }
           }).start();
       }
    }








}
