package com.example.algorithm;

//数组全排列
public class FullSort {

    public static void main(String[] args) {
        int[] data = {1,2,3,4};
        fullSort(data,0,data.length-1);
    }

    public static void fullSort(int[] data,int start,int end){

        //终止条件
        if (start == end){
            for (int i:data) {
                System.out.print(i);
            }
            System.out.println();
            return;
        }

        for (int i = start;i<=end;i++){
            swap(data,i,start);
            fullSort(data,start+1,end);
            swap(data,i,start);
        }
    }

    private static void swap(int[] data,int a,int b){
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }
}
