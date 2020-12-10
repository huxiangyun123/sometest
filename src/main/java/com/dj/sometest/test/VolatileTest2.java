package com.dj.sometest.test;

/**
 * @Author: Chris
 * @Date: 2020/12/5 13:43
 */
public class VolatileTest2 {

    boolean flag = true;

    public static void main(String[] args) {

        VolatileTest2 obj = new VolatileTest2();

        new Thread(() -> {
            System.out.println("t1 start");
            while (obj.flag){
                //System.out.println("t1==>"+obj.flag);
            }
            System.out.println("t1 end");
        },"t1").start();

        new Thread(() -> {
            try {
                System.out.println("t2 start");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            obj.flag = false;
            System.out.println("t2 end");
        },"t2").start();
    }
}
