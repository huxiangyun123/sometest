package com.dj.sometest;

import com.alibaba.fastjson.JSON;
import com.dj.sometest.entity.User;
import com.dj.sometest.mapper.UserMapper;
import com.dj.sometest.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.InputStream;
import java.util.*;
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
    @Autowired
    RedisTemplate redisTemplate;




    @Test
    void test1() {
        User user = userMapper.selectById(1);

        System.out.println(user);

    }




}
