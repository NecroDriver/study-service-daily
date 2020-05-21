package com.xin.daily.controller.exception;

/**
 * @author creator mafh 2019/11/18 16:55
 * @author updater
 * @version 1.0.0
 */
public class ExceptionTest {

    public static void main(String[] args) {
        try {
            int x = 1/0;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(88888);
            return;
        }
        System.out.println(111123);
    }
}
