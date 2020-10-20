package com.xin.daily.study.design.observe;

/**
 * Rabbit
 *
 * @author mfh 2020/10/20 11:49
 * @version 1.0.0
 **/
public class Rabbit implements Observer {
    @Override
    public void update() {
        System.out.println("兔子过来吃了胡萝卜");
    }
}
