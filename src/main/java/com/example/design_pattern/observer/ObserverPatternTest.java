package com.example.design_pattern.observer;

public class ObserverPatternTest {

    public static void main(String[] args) {
        TeacherSubject teacher = new TeacherSubject();
        StudentObserver studentObserver1 = new StudentObserver("jack", teacher);
        StudentObserver studentObserver2 = new StudentObserver("mike",teacher);
        StudentObserver studentObserver3 = new StudentObserver("amy",teacher);

        teacher.setHomework("语文");
        teacher.setHomework("数学");
    }
}
