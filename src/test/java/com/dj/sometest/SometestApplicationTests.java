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


    }









}
