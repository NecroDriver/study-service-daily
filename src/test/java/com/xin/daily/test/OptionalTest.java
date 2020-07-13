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
        String s = "a";
        Optional<Object> str = Optional.ofNullable(s).map(a -> create());
    }

    public static String create(){
        System.out.println("create something...");
        return "aaaaa";
    }
}
