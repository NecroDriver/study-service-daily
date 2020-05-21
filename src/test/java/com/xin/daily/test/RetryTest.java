package com.xin.daily.test;

import com.xin.web.utils.retry.RetryUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author creator mafh 2019/12/19 16:58
 * @author updater
 * @version 1.0.0
 */
public class RetryTest {

    public static List foo() {
        int rand = (int) (Math.random() * 10);
        // 显示抛出异常
        System.out.println("调用方法 " + rand);
        rand = rand < 5 ? 0 : rand;
        // 模拟抛出异常
        System.out.println(1 / rand);
        List list = new ArrayList<>();
        list.add("1");
        return list;
    }

    public static void main(String[] args) {
        try {
            foo();
        } catch (Exception e) {
            try {
                RetryUtils.retry(10, () -> foo(), (list, e1) -> e1 != null || list == null || list.isEmpty());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
