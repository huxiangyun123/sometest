package com.dj.sometest.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟资源类，有3个空车位

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    boolean b = semaphore.tryAcquire();
                    if(b){
                        System.out.println(Thread.currentThread().getName() + "抢占成功");
                    }else {
                        System.out.println(Thread.currentThread().getName() + "抢占失败");
                    }
                    //暂停一会儿线程
                    TimeUnit.SECONDS.sleep(3);
                    //System.out.println(Thread.currentThread().getName() + "\t离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }

}
