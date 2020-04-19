package com.example.javabase.jvm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.stream.IntStream;

public class Test {

        public static void main(String[] args) throws ClassNotFoundException {
//            SingleTon singleTon = SingleTon.getInstance();
//            System.out.println("count1=" + singleTon.count1);
//            System.out.println("count2=" + singleTon.count2);


//            A ab = new B();
//            ab.say();
//            ab = new B();
//            ab.say();

//            A a = new A();


//            StringBuffer a = new StringBuffer("nowcoder");
//            StringBuffer b = new StringBuffer("com");
//            a.delete(1,3);
//            a.append(b);
//            System.out.println(a);
//
//            Thread thread = new Thread();
//            thread.wait();

//            ArrayList<String> l1 = new ArrayList<String>();
//            ArrayList<Integer> l2 = new ArrayList<Integer>();
//            l1.add("1");
//            l2.add(1);
//            System.out.println(l1.get(0).getClass());
//            System.out.println(l2.get(0).getClass());
//            System.out.println(l1.getClass() == l2.getClass());

            //令 result 的值为 不大于 100 的正偶数的平方和
            int res = IntStream.rangeClosed(1,10).filter(n -> n % 2 == 0).map(n -> n * n).sum();
            System.out.println(res);
        }

//    private static final ArrayList<String> list = new ArrayList<>();
//    public static String test(String j){
//        int i = 1, s = 1, f = 1, a = 1, b = 1,c = 1,d = 1,e = 1;
//        list.add(new String("11111111111111111111111111111"));
//        return test(s+i+f+a+b+c+d+e+"");
//    }

    void A(){

    }
}
