package com.example.design_pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class TeacherSubject implements Subject {

    List<Observer> observers;
    String homework;

    public TeacherSubject() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observer == null || !observers.contains(observer))
            throw new RuntimeException("没有找到这个observer");
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (int i = 0;i < observers.size();i++){
            observers.get(i).updateState(homework);
        }
    }

    public void setHomework(String homework){
        this.homework = homework;
        System.out.println("teacher set homework -- "+homework);
        notifyObserver();
    }
}
