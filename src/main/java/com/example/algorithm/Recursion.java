package com.example.algorithm;

import java.io.File;
import java.util.Arrays;

public class Recursion {

    public static void main(String[] args){
//        Integer[] datas = {1,1,1,1,1,1,1,1};
//        System.out.println(sum(datas,5));
//        System.out.println(sum_1(datas,5));
//        System.out.println(digui(6));

        String path = "/Users/jiadao/Downloads";
        file(path);
    }
     public static Integer sum(Integer[] datas,Integer n){
        Integer sum = 0;
        while(n>0){
            sum = sum + datas[--n];
        }
        return sum;
     }

    public static Integer sum_1(Integer[] datas,Integer n){
        Integer sum = 0;
        if (n==0)
            return 0;
        return sum_1(datas,n-1)+datas[n-1];
    }

    //累乘
    public static Integer digui(int n){
        if (n==0 || n==1){
            return n;
        }
        return digui(n-1)*n;
    }

    //使用递归算法输出某个目录下所有文件和子目录列表
    public static void file(String path){
        File file = new File(path);
        File[] files = file.listFiles();
        if (files.length == 0){
            return;
        }
        for (File f:files) {
            if (f.isFile()){
                System.out.println(f.getPath());
            } else {
                System.out.println(f.getName());
                file(f.getPath());
            }
        }
    }

}
