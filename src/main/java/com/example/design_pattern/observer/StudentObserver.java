package com.example.design_pattern.observer;

public class StudentObserver implements Observer {

    String name;
    TeacherSubject teacherSubject;

    public StudentObserver(String name, TeacherSubject teacherSubject) {
        this.name = name;
        this.teacherSubject = teacherSubject;
        this.teacherSubject.addObserver(this);
    }

    @Override
    public void updateState(String info) {
        System.out.println("student -- "+name + "接受作业--"+ info);
    }
}
