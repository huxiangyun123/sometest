package com.dj.sometest.test;

/**
 * @Author: Chris
 * @Date: 2020/12/5 14:04
 */
public class VolatileTest3 {

     boolean flag = true;


    public static void main(String[] args) throws InterruptedException {
        VolatileTest3 obj = new VolatileTest3();

        new Thread(() -> {
            System.out.println("m start");
            while (obj.flag){
                //System.out.println("t1==>"+obj.flag);
            }
            System.out.println("m end");
        },"t1").start();

        Thread.sleep(3000);
        obj.flag =  false;
    }
}
