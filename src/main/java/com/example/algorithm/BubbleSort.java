package com.example.algorithm;

import java.util.Arrays;
import java.util.List;

public class BubbleSort {

    public static void main(String[] args){
        Integer[] datas = {5,2,7,4,6,3,1};
//        System.out.println(bubbleSort(Arrays.asList(datas)));
        System.out.println(bubbleSort_1(Arrays.asList(datas),datas.length));
    }

    public static List<Integer> bubbleSort(List<Integer> list){
        if (list.isEmpty())
            throw new RuntimeException("list is empty");
        for (int i=0;i<list.size();i++){
            for (int j=1;j<list.size();j++){
                if (list.get(j-1)>list.get(j)){
                    int temp = list.get(j-1);
                    list.set(j-1,list.get(j));
                    list.set(j,temp);
                }
            }
        }
        return list;
    }

    public static List<Integer> bubbleSort_1(List<Integer> list,Integer size){
        if (list.isEmpty())
            throw new RuntimeException("list is empty");
        for (boolean sorted=false;sorted = !sorted;size--){
            for (int j=1;j<list.size();j++){
                if (list.get(j-1)>list.get(j)){
                    int temp = list.get(j-1);
                    list.set(j-1,list.get(j));
                    list.set(j,temp);
                    sorted = false;
                }
            }
        }
        return list;
    }
}
