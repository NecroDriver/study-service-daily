package com.xin.daily.test;

import javax.mail.search.NotTerm;
import java.time.LocalDateTime;

/**
 * @author creator mafh 2019/11/25 11:30
 * @author updater
 * @version 1.0.0
 */
public class TimeTest {

    public static void main(String[] args) {
        LocalDateTime nowTime = LocalDateTime.now();

        System.out.println(nowTime);
        System.out.println(nowTime.getHour());
    }
}
