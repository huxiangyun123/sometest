package com.dj.sometest.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: Chris
 * @Date: 2021/1/28 23:56
 */
public class CallableTest implements Callable {


    @Override
    public Object call() throws Exception {
        return "finish";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new CallableTest());
        //FutureTask实现了Runnale
        Thread thread = new Thread(task);
        //start后会调用到FutureTask的run方法
        thread.start();
        Object o = task.get();
        System.out.println(o);
    }
}
