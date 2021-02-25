package com.dj.sometest.controller;

import com.alibaba.excel.EasyExcel;
import com.dj.sometest.entity.User;
import com.dj.sometest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: Chris
 * @Date: 2020/9/25 18:27
 */
@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;


    @GetMapping("/test222")
    public String test(){
        return "test222";
    }


}
