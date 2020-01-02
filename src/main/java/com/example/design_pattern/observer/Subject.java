package com.example.design_pattern.observer;


/**
 * 观察者模式Subject(主题接口)
 */
public interface Subject {

    //添加观察者
    void addObserver(Observer observer);

    //删除观察者
    void removeObserver(Observer observer);

    //主题变动时提醒观察者
    void notifyObserver();
}
