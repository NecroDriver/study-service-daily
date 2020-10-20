package com.xin.daily.study.design.observe;

/**
 * Breeder 饲养员
 *
 * @author mfh 2020/10/20 11:57
 * @version 1.0.0
 **/
public class Breeder extends ObservedObject {
    public void feed(){
        System.out.println("饲养员出来喂食");
        notifyObservers();
    }

    public static void main(String[] args) {
        // 对象声明
        Breeder breeder = new Breeder();
        Rabbit rabbit = new Rabbit();
        Dog dog = new Dog();
        Monkey monkey = new Monkey();
        // 注册观察者
        breeder.addObserver(rabbit);
        breeder.addObserver(dog);
        breeder.addObserver(monkey);
        // 饲养员喂食操作
        breeder.feed();
    }
}
