package com.xin.daily.study.semaphore;

import java.time.LocalDateTime;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Student
 *
 * @author lemon 2020/6/19 15:24
 * @version 1.0.0
 **/
public class Student extends Thread {
    /**
     * 信号量
     */
    private final Semaphore semaphore;
    /**
     * 学生姓名
     */
    private final String name;

    public Student(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(LocalDateTime.now() + " " + name + " join");
            semaphore.acquire();
            System.out.println(LocalDateTime.now() + " " + name + " start");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(LocalDateTime.now() + " " + name + " end");
            semaphore.release();
        }
    }

}
