package com.xin.daily.test;

import org.springframework.util.Assert;

/**
 * @author creator mafh 2019/11/27 14:16
 * @author updater
 * @version 1.0.0
 */
public class DailyTest {

    public static void main(String[] args) {
        String str = null;
        String sss = str.toString();
        Assert.notNull(sss, "sssssss");
        System.out.println(sss);
    }
}
