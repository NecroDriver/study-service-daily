package com.xin.daily.test;

import java.util.Optional;

/**
 * OptionalTest
 *
 * @author lemon 2020/6/5 17:06
 * @version 1.0.0
 **/
public class OptionalTest {

    public static void main(String[] args) {
        String s = null;
        String str = Optional.ofNullable(s).orElseGet(OptionalTest::create);
        System.out.println(str);
    }

    public static String create(){
        System.out.println("create something...");
        return "aaaaa";
    }
}
