package com.xin.daily.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author creator mafh 2019/11/27 14:16
 * @author updater
 * @version 1.0.0
 */
public class DailyTest {

    public static void main(String[] args) throws InterruptedException {

        BigDecimal commission = BigDecimal.ONE.multiply(BigDecimal.valueOf(20)).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        System.out.println(commission);
    }
}
