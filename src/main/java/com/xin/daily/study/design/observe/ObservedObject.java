package com.xin.daily.study.design.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * ObservedObject 被观察对象
 *
 * @author mfh 2020/10/20 11:14
 * @version 1.0.0
 **/
public abstract class ObservedObject {

    private final List<Observer> observerList = new ArrayList<>();

    public void addObserver(Observer observer){
        observerList.add(observer);
    }

    public void delObserver(Observer observer){
        observerList.remove(observer);
    }

    public void notifyObservers(){
        observerList.forEach(Observer::update);
    }
}
