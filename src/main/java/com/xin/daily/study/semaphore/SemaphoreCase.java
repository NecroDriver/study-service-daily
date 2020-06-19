package com.xin.daily.study.semaphore;

import java.util.concurrent.Semaphore;

/**
 * SemaphoreCase 信号量样例
 *
 * @author lemon 2020/6/19 15:12
 * @version 1.0.0
 **/
public class SemaphoreCase {
    /**
     * 信号量 允许运行线程数：3
     */
    private static Semaphore semaphore = new Semaphore(3);
    public static void main(String[] args) {
        // 10个学生去食堂打饭，只有三个窗口
        for (int i = 0; i < 10; i++) {
            new Student(semaphore, i + "").start();
        }
    }
}
