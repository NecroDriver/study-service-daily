package com.xin.daily.study.design.observe;

/**
 * Monkey
 *
 * @author mfh 2020/10/20 11:54
 * @version 1.0.0
 **/
public class Monkey implements Observer{
    @Override
    public void update() {
            System.out.println("猴子拿走了桃子");
    }
}
