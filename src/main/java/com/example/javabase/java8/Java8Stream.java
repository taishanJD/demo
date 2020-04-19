package com.example.javabase.java8;

import java.util.Scanner;

public class Java8Stream {

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int K = s.nextInt();
        String S = s.next();
        System.out.println(tranverse(K,S));
    }
    public static String tranverse(int K,String S){
        int first = S.indexOf("-");
        if (first < 0){
            return S;
        }
        String s1 = S.substring(0,first+1);
        String s2 = S.substring(first+1,S.length());
        s2 = s2.toUpperCase().replace("-","");
        StringBuilder sb = new StringBuilder(s2);
        int len = sb.length();
        for (int i = 1;i<len;i+=K){
            sb.insert(i,'-');
        }
        return s1+sb.toString();
    }

}
