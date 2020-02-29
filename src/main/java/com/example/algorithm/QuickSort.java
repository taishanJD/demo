package com.example.algorithm;

import java.util.Collections;

public class QuickSort {

    public static void main(String[] args) {
        int[] datas = {1, 7, 6, 0, 0, 7, 3, 5, 9, 1, 4};
        print(datas);
        quickSort(datas, 0, datas.length - 1);
        print(datas);
    }

    public static void quickSort(int[] datas, int left, int right) {
        if (left > right)
            return;
        int i = left, j = right;
        int temp = datas[i];
        while (i < j) {
            while (i < j && temp <= datas[j]) {
                j--;
            }
            while (i < j && temp >= datas[i]) {
                i++;
            }
            if (i < j) {
                int temp1 = datas[i];
                datas[i] = datas[j];
                datas[j] = temp1;
            }
        }
        datas[left] = datas[i];
        datas[i] = temp;

        quickSort(datas, left, j - 1);
        quickSort(datas, j + 1, right);
    }

    private static void print(int[] datas) {
        for (int i = 0; i < datas.length; i++) {
            System.out.print(datas[i] + " ");
        }
        System.out.println();
    }
}
