package com.dj.sometest.controller;

import com.dj.sometest.annotation.MyAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Chris
 * @Date: 2020/8/25 15:48
 */
@MyAnnotation("test")
@RestController
public class AopController {



    @GetMapping("/aop01")
    public void testaop1() {
        System.out.println("testaop1==========");
    }

    @MyAnnotation(value = "test")
    @GetMapping("/aop02/{id}")
    public void testaop2(@PathVariable("id") String id) {
        System.out.println("testaop2==========");
    }


    @GetMapping("/t3")
    public void test() {

        System.out.println("testaop2==========");
    }



}
