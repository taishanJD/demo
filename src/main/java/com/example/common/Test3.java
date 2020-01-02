package com.example.common;

import java.lang.reflect.Field;

class Test3 extends Test2{
    public Test3() {
        System.out.println("b");
    }

    public static void main(String[] args) {
        Test3 test3 = new Test3();
        Field[] fields = Test3.class.getDeclaredFields();
        Field[] fields1 = Test3.class.getFields();
        System.out.println("args = [" + args + "]");

        System.out.println(""+("12"=="12"&&"12".equals("12")));

    }

    public void test33(){}

    @Override
    public void test3() {
        super.test3();
    }
}


