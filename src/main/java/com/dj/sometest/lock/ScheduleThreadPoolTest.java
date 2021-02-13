package com.dj.sometest.lock;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Chris
 * @Date: 2021/2/13 22:44
 */
public class ScheduleThreadPoolTest {

    public static void main(String[] args) {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

        executor.schedule(() ->{
            System.out.println("延迟3秒执行");
        },3, TimeUnit.SECONDS);

        executor.scheduleAtFixedRate(() -> {
            System.out.println("延迟2秒后，每隔5秒执行");
            //executor单线程池
            //此任务执行时间很长，并且只有一个线程，那么任务就会产生堆积
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },2,5,TimeUnit.SECONDS);

        executor.scheduleWithFixedDelay(() -> {
            //这种方式不会产生任务堆积
            System.out.println("延迟2秒后，每次任务执行完后延迟5s再执行");
        },2,5,TimeUnit.SECONDS);
    }
}
