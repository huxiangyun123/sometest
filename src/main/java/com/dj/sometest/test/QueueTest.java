package com.dj.sometest.test;

import java.util.concurrent.*;

/**
 * @Author: Chris
 * @Date: 2021/2/8 21:50
 */
public class QueueTest {

    public static void main(String[] args) {

        /**
         * 1.加入元素，包含三种方式  put  add  offer
         *            put 加入元素的时候如果队列已满则会阻塞等待
         *            add加入元素的时候如果队列已满则会抛出异常
         *            offer加入元素的时候如果队列已满则会返回false
         * 2.去除元素，包含三种方式take remove poll
         *            take 如果队列为空则会阻塞等待
         *            remove 如果队列为空则会抛出异常
         *            poll 如果队列为空会返回null
         */
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(100);
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 10; i++) {
            MyThread myThread = new MyThread(i);
            queue.add(myThread);
        }

        /*while (queue.size()>0){
            pool.submit(queue.poll());
        }*/

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 100, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(Integer.MAX_VALUE / 2));
        while (queue.size()>0){
            executor.execute(queue.poll());
        }
    }


    static class MyThread implements Runnable {

        int i = 0;

        public MyThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println("线程号:" + Thread.currentThread().getName() + "===>" + i);
        }
    }
}
