package com.xin.daily.test;

/**
 * @author creator mafh 2019/12/18 13:55
 * @author updater
 * @version 1.0.0
 */
public class StaticTest {

    public static void main(String[] args) {
        System.out.println("第1个月的兔子对数: 1");

        System.out.println("第2个月的兔子对数: 1");

        int f1 = 1, f2 = 1, f, M = 24;

        for (int i = 3; i <= M; i++) {

            f = f2;

            f2 = f1 + f2;

            f1 = f;

            System.out.println("第" + i + "个月的兔子对数: " + f2);

        }
    }
}
