package com.dj.sometest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.sometest.entity.C;
import com.dj.sometest.entity.UserEntity;
import com.dj.sometest.mapper.UserMapper;
import com.dj.sometest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    C c;

    public void test(){
        c.c();
    }


    public void setC(C c) {
        this.c = c;
    }
}