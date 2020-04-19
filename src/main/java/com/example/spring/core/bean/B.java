package com.example.spring.core.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class B {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext(
                "src/main/resources/applicationContext.xml");

        B b = (B) context.getBean("b");
        System.out.println(b);
    }
}
