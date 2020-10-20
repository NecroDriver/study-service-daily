package com.xin.daily.study.design.observe;

/**
 * Dog
 *
 * @author mfh 2020/10/20 11:56
 * @version 1.0.0
 **/
public class Dog implements Observer {
    @Override
    public void update() {
        System.out.println("狗啥也没有，只能叫了两声");
    }
}
