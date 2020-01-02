package com.example.design_pattern.observer;

/**
 * 观察者模式Observer(观察者接口)
 * */
public interface Observer {

    //订阅的主题变化时，观察者自身的变化
    public void updateState(String info);
}
