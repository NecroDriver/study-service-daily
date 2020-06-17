package com.xin.daily.test;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ThreadTest 线程测试
 *
 * @author lemon 2020/6/17 10:19
 * @version 1.0.0
 **/
public class ThreadTest {

    private static final BlockingQueue<Thread> queue = new ArrayBlockingQueue<>(3);

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
//            for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName());
//            }
        };
        Thread t1 = new Thread(runnable, "a");
        Thread t2 = new Thread(runnable, "b");
        Thread t3 = new Thread(runnable, "c");
        queue.add(new Thread(runnable, "a"));
        queue.add(new Thread(runnable, "b"));
        queue.add(new Thread(runnable, "c"));
        for (int i = 0; i < 10; i++) {
            while (!queue.isEmpty()) {
                queue.take().start();
                TimeUnit.MILLISECONDS.sleep(2);
            }
            queue.add(new Thread(runnable, "a"));
            queue.add(new Thread(runnable, "b"));
            queue.add(new Thread(runnable, "c"));
        }
    }
}
